package cc.elvea.platform.system.message.api.impl;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.enums.BaseEnum;
import cc.elvea.platform.commons.message.rabbit.RabbitUtils;
import cc.elvea.platform.commons.utils.*;
import cc.elvea.platform.commons.utils.template.HtmlTemplateService;
import cc.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import cc.elvea.platform.system.core.service.UserService;
import cc.elvea.platform.system.message.api.MessageApi;
import cc.elvea.platform.system.message.enums.MessageStatusEnum;
import cc.elvea.platform.system.message.enums.MessageTargetTypeEnum;
import cc.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cc.elvea.platform.system.message.enums.MessageUserTypeEnum;
import cc.elvea.platform.system.message.model.dto.CreateMessageDto;
import cc.elvea.platform.system.message.model.dto.MessageUserDto;
import cc.elvea.platform.system.message.model.dto.SendMessageAmqpDto;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.model.entity.*;
import cc.elvea.platform.system.message.sender.*;
import cc.elvea.platform.system.message.service.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static cc.elvea.platform.system.commons.constants.SystemMessageConstants.TPL_CLASSPATH;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageApiImpl implements MessageApi {

    private HtmlTemplateService htmlTemplateService;

    private MessageService messageService;

    private MessageTypeService messageTypeService;

    private MessageUserService messageUserService;

    private MessageContentService messageContentService;

    private MessageTemplateService messageTemplateService;

    private MessageTemplateTypeService messageTemplateTypeService;

    private RabbitUtils amqpUtils;

    private UserService userService;

    /**
     * @see MessageApi#createMessage(CreateMessageDto)
     */
    @Override
    public Long createMessage(CreateMessageDto message) throws Exception {
        log.info("Create message. type - [{}] type id - [{}]. start.", message.getType(), message.getTypeId());

        // 获取消息类型
        MessageTypeEntity messageTypeEntity = null;
        if (StringUtils.isNotEmpty(message.getType())) {
            messageTypeEntity = messageTypeService.findByCode(message.getType());
        } else if (message.getTypeId() != null && message.getTypeId() > 0) {
            messageTypeEntity = messageTypeService.findById(message.getTypeId());
        }

        if (messageTypeEntity == null) {
            log.info("Create message. type - [{}] type id - [{}]. Invalid message type.", message.getType(), message.getTypeId());
            return 0L;
        }

        // 检查消息类型是否是正常并开启
        if (!messageTypeEntity.getActive()) {
            log.info("Create message. type - [{}]. Inactive message type.", messageTypeEntity.getCode());
            return 0L;
        }

        if (messageTypeEntity.getStatus() != 1) {
            log.info("Create message. type - [{}]. Disabled message type.", messageTypeEntity.getCode());
            return 0L;
        }

        // -------------------------------------------------------------------------------------------------------------
        // 保存消息记录
        // -------------------------------------------------------------------------------------------------------------

        MessageEntity entity = MessageEntity.builder()
                .typeId(messageTypeEntity.getId())
                .subject(StringUtils.isNotEmpty(message.getSubject()) ? message.getSubject() : messageTypeEntity.getTitle())
                .content(message.getContent())
                .data(MapUtils.isNotEmpty(message.getParams()) ? JacksonUtils.toJson(message.getParams()) : "")
                .status(MessageStatusEnum.PENDING.getValue())
                .build();

        this.messageService.save(entity);

        Long messageId = entity.getId();

        log.info("Create message. type - [{}] id - [{}]. created.", messageTypeEntity.getCode(), messageId);

        // -------------------------------------------------------------------------------------------------------------
        // 处理消息用户
        // -------------------------------------------------------------------------------------------------------------

        log.info("Create message. type - [{}] id - [{}]. user start.", messageTypeEntity.getCode(), messageId);

        List<MessageUserEntity> userEntityList = Lists.newArrayList();
        // 发件人
        userEntityList.add(createMessageUser(messageId, message.getSender()));
        // 收件人
        if (!CollectionUtils.isEmpty(message.getRecipients())) {
            for (MessageUserDto user : message.getRecipients()) {
                userEntityList.add(createMessageUser(messageId, user));
            }
        }
        this.messageUserService.saveBatch(userEntityList);
        log.info("Create message. type - [{}] id - [{}]. user done.", messageTypeEntity.getCode(), messageId);

        // -------------------------------------------------------------------------------------------------------------
        // 处理消息内容
        // 如果指定了模版，那将沿用指定的模版来发送消息
        // 未指定的情况，则用系统已经保存并启用的模版来发送
        // -------------------------------------------------------------------------------------------------------------

        log.info("Create message. type - [{}] id - [{}]. content start.", messageTypeEntity.getCode(), messageId);

        List<MessageTemplateTypeEnum> templateTypeList = CollectionUtils.isEmpty(message.getTemplateTypeList()) ?
                Arrays.stream(MessageTemplateTypeEnum.values()).toList() : message.getTemplateTypeList();

        List<MessageContentEntity> contentEntityList = Lists.newArrayList();
        for (MessageTemplateTypeEnum templateType : templateTypeList) {
            log.info("Create message. type - [{}] id - [{}] template [{}]. start.", messageTypeEntity.getCode(), messageId, templateType.getCode());

            MessageTemplateTypeEntity templateTypeEntity = messageTemplateTypeService.findByCode(templateType.getCode());

            // 检查消息模版类型是否存在并处于正常开启状态
            if (templateTypeEntity == null) {
                log.info("Create message. type - [{}] id - [{}] template [{}]. invalid template type.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                continue;
            }
            if (!templateTypeEntity.isActiveEntity()) {
                log.info("Create message. type - [{}] id - [{}] template [{}]. inactive template type.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                continue;
            }
            if (templateTypeEntity.getStatus() != 1) {
                log.info("Create message. type - [{}] id - [{}] template [{}]. disabled template type.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                continue;
            }

            String content = "";
            if (StringUtils.isNotEmpty(message.getContent())) {
                log.info("Create message. type - [{}] id - [{}] template [{}]. use content.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                content = message.getContent();
            } else if (StringUtils.isNotEmpty(message.getTemplate())) {
                log.info("Create message. type - [{}] id - [{}] template [{}]. use template.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                content = htmlTemplateService.toHtml(message.getTemplate(), message.getParams());
            } else {
                log.info("Create message. type - [{}] id - [{}] template [{}]. use system template.", messageTypeEntity.getCode(), messageId, templateType.getCode());

                MessageTemplateEntity messageTemplateEntity = this.messageTemplateService.findByType(messageTypeEntity.getId(), templateTypeEntity.getId());

                // 检查消息模版是否存在并处于正常开启状态
                if (messageTemplateEntity == null) {
                    log.info("Create message. type - [{}] id - [{}] template [{}]. invalid message template.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                    continue;
                }
                if (!messageTemplateEntity.isActiveEntity()) {
                    log.info("Create message. type - [{}] id - [{}] template [{}]. inactive message template.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                    continue;
                }
                if (messageTemplateEntity.getStatus() != 1) {
                    log.info("Create message. type - [{}] id - [{}] template [{}]. disabled message template.", messageTypeEntity.getCode(), messageId, templateType.getCode());
                    continue;
                }
                content = htmlTemplateService.toHtml(messageTemplateEntity.getContent(), message.getParams());
            }

            MessageContentEntity contentEntity = MessageContentEntity.builder()
                    .messageId(entity.getId())
                    .templateTypeId(templateTypeEntity.getId())
                    .content(content)
                    .build();
            contentEntityList.add(contentEntity);
        }

        if (CollectionUtils.isNotEmpty(contentEntityList)) {
            this.messageContentService.saveBatch(contentEntityList);
            log.info("Create message. type - [{}] id - [{}]. save content.", messageTypeEntity.getCode(), messageId);
        } else {
            log.info("Create message. type - [{}] id - [{}]. empty content.", messageTypeEntity.getCode(), messageId);
        }
        log.info("Create message. type - [{}] id - [{}]. content done.", messageTypeEntity.getCode(), messageId);

        log.info("Create message. type - [{}] id - [{}]. done.", messageTypeEntity.getCode(), messageId);

        // 如果是立刻发送，消息创建完成后，直接推到消息队列
        if (MessageTargetTypeEnum.IMMEDIATE.equals(message.getTargetType())) {
            this.amqpUtils.send(SystemAmqpConstants.MESSAGE_QUEUE, SendMessageAmqpDto.builder().id(messageId).build());
        }
        return messageId;
    }

    /**
     * 这个方法特别留意需要允许读取其他未提交的数据，这样可以避免高并发的时候重复推送消息
     *
     * @see MessageApi#sendMessage()
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void sendMessage() throws Exception {
        log.info("Send message. start.");
        List<MessageEntity> messageEntityList = this.messageService.findByStatus(Collections.singletonList(MessageStatusEnum.PENDING));
        if (CollectionUtils.isNotEmpty(messageEntityList)) {
            log.info("Send message. {} messages found..", messageEntityList.size());
            for (MessageEntity messageEntity : messageEntityList) {
                try {
                    log.info("Send message. id - [{}]. start.", messageEntity.getId());

                    // 修改消息发送状态为发送中
                    messageEntity.setStatus(MessageStatusEnum.SENDING.getValue());
                    this.messageService.save(messageEntity);

                    // 推送到消息队列
                    this.amqpUtils.send(SystemAmqpConstants.MESSAGE_QUEUE, SendMessageAmqpDto.builder().id(messageEntity.getId()).build());

                    log.info("Send message. id - [{}]. done.", messageEntity.getId());
                } catch (Exception e) {
                    log.error("Send message. id - [{}]. failed.", messageEntity.getId(), e);
                }
            }
        } else {
            log.info("Send message. no messages found.");
        }
        log.info("Send message. done.");
    }

    /**
     * @see MessageApi#sendMessage(Long)
     */
    @Override
    public void sendMessage(Long messageId) throws Exception {
        this.sendMessage(messageId, false);
    }

    /**
     * @see MessageApi#sendMessage(Long, boolean)
     */
    @Override
    public void sendMessage(Long messageId, boolean force) throws Exception {
        log.info("Send message [{}] force [{}]. start.", messageId, force);

        // 查询消息记录
        MessageEntity messageEntity = this.messageService.findById(messageId);
        if (messageEntity == null) {
            log.info("Send message [{}] force [{}]. invalid id.", messageId, force);
            return;
        }

        // 查询消息类型记录
        // 1. 找不到类型记录，跳过
        // 2. 消息类型记录如果是未启用状态或者未发布，那跳过不发送消息，
        Long messageTypeId = messageEntity.getTypeId();
        MessageTypeEntity messageTypeEntity = this.messageTypeService.findCacheById(messageTypeId);
        if (messageTypeEntity == null) {
            log.info("Send message [{}]. invalid message type [{}].", messageId, messageTypeId);
            return;
        }
        if (messageTypeEntity.isInactiveEntity()) {
            log.info("Send message [{}]. inactive message type [{}].", messageId, messageTypeId);
            return;
        }

        // 查询消息用户记录
        // 1. 找不到类型记录，跳过
        // 2. 消息类型记录如果是未启用状态或者未发布，那跳过不发送消息，
        List<MessageUserEntity> messageUserEntityList = this.messageUserService.findByMessage(messageId);
        if (CollectionUtils.isEmpty(messageUserEntityList)) {
            log.info("Send message [{}]. no message user found.", messageId);
            return;
        }

        List<MessageUserEntity> sender = Lists.newArrayList();
        List<MessageUserEntity> recipients = Lists.newArrayList();
        for (MessageUserEntity messageUserEntity : messageUserEntityList) {
            MessageUserTypeEnum messageUserTypeEnum = BaseEnum.getEnumByValue(messageUserEntity.getTypeId(), MessageUserTypeEnum.class);
            if (MessageUserTypeEnum.FROM.equals(messageUserTypeEnum)) {
                sender.add(messageUserEntity);
            } else if (MessageUserTypeEnum.TO.equals(messageUserTypeEnum)) {
                recipients.add(messageUserEntity);
            }
        }
        if (CollectionUtils.isEmpty(sender)) {
            log.info("Send message [{}]. no sender found.", messageId);
            return;
        }
        if (CollectionUtils.isEmpty(recipients)) {
            log.info("Send message [{}]. no recipient found.", messageId);
            return;
        }

        // 查询消息用户记录
        // 1. 找不到类型记录，跳过
        // 2. 消息类型记录如果是未启用状态或者未发布，那跳过不发送消息，
        List<MessageContentEntity> messageContentEntityList = this.messageContentService.findByMessage(messageId);
        if (CollectionUtils.isEmpty(messageContentEntityList)) {
            log.info("Send message [{}]. no message content found.", messageId);
            return;
        }
        for (MessageContentEntity messageContentEntity : messageContentEntityList) {
            log.info("Send message [{}]. handle message content id [{}] templateTypeId [{}].",
                    messageId, messageContentEntity.getMessageId(), messageContentEntity.getTemplateTypeId());

            MessageTemplateTypeEntity messageTemplateTypeEntity = this.messageTemplateTypeService.findById(messageContentEntity.getTemplateTypeId());
            if (messageTemplateTypeEntity == null) {
                log.info("Send message [{}]. handle message content id [{}] templateTypeId [{}]. invalid template type.",
                        messageId, messageContentEntity.getMessageId(), messageContentEntity.getTemplateTypeId());
                continue;
            }

            MessageTemplateTypeEnum messageTemplateTypeEnum = BaseEnum.getEnumByValue(messageTemplateTypeEntity.getCode(), MessageTemplateTypeEnum.class);
            MessageSender messageSender = getMessageSender(messageTemplateTypeEnum);
            if (messageSender == null) {
                log.info("Send message [{}]. handle message content id [{}] templateTypeId [{}]. invalid sender.",
                        messageId, messageContentEntity.getMessageId(), messageContentEntity.getTemplateTypeId());
                continue;
            }

            for (MessageUserEntity senderEntity : sender) {
                for (MessageUserEntity recipientEntity : recipients) {
                    SendMessageDto sendMessageDto = SendMessageDto.builder()
                            .id(messageEntity.getId())
                            .contentId(messageContentEntity.getId())
                            .subject(messageEntity.getSubject())
                            .content(messageContentEntity.getContent())
                            .recipient(this.messageUserService.getRecipient(recipientEntity))
                            .sender(this.messageUserService.getSender(senderEntity))
                            .build();
                    messageSender.send(sendMessageDto);
                }
            }

            // 更新消息的发送状态和发送时间
            messageEntity.setSentDatetime(this.messageService.getCurLocalDateTime());
            messageEntity.setStatus(MessageStatusEnum.SENT.getValue());
            this.messageService.save(messageEntity);
        }
        log.info("Send message [{}]. done.", messageId);
    }

    /**
     * @see MessageApi#initTemplate()
     */
    @Override
    public void initTemplate() throws Exception {
        log.info("Init message template. start.");

        List<MessageTypeEntity> messageTypeEntityList = this.messageTypeService.findAll();
        if (CollectionUtils.isEmpty(messageTypeEntityList)) {
            log.info("Init message template. empty message type. skip.");
            return;
        }

        List<MessageTemplateTypeEntity> messageTemplateTypeEntityList = this.messageTemplateTypeService.findAll();
        if (CollectionUtils.isEmpty(messageTemplateTypeEntityList)) {
            log.info("Init message template. empty message template type. skip.");
            return;
        }

        for (MessageTypeEntity messageTypeEntity : messageTypeEntityList) {
            log.info("Init message template for type [{}]. start.", messageTypeEntity.getCode());
            for (MessageTemplateTypeEntity messageTemplateTypeEntity : messageTemplateTypeEntityList) {
                Long id = Long.valueOf(String.valueOf(messageTypeEntity.getId()).concat(String.valueOf(messageTemplateTypeEntity.getId())));

                MessageTemplateEntity messageTemplateEntity = this.messageTemplateService.findById(id);
                if (messageTemplateEntity == null) {
                    log.info("Init message template for type [{}]. create message template.", messageTypeEntity.getCode());
                    messageTemplateEntity = new MessageTemplateEntity();
                    messageTemplateEntity.setId(id);
                    messageTemplateEntity.setTypeId(messageTypeEntity.getId());
                    messageTemplateEntity.setTemplateTypeId(messageTemplateTypeEntity.getId());
                    messageTemplateEntity.setContent("");
                    this.messageTemplateService.insert(messageTemplateEntity);
                } else {
                    log.info("Init message template for type [{}]. message template exists. skip.", messageTypeEntity.getCode());
                }
            }
            log.info("Init message template for type [{}]. done.", messageTypeEntity.getCode());
        }

        log.info("Init message template. done.");
    }

    /**
     * @see MessageApi#syncTemplate(boolean)
     */
    @Override
    public void syncTemplate(boolean force) throws Exception {
        log.info("Sync message template force [{}]. start.", force);

        // 查询当前所有消息类型
        List<MessageTypeEntity> messageTypeEntityList = this.messageTypeService.findAll();
        if (CollectionUtils.isEmpty(messageTypeEntityList)) {
            log.info("Sync message template. no message type found.");
            return;
        }

        // 查询当前所有消息模版类型
        List<MessageTemplateTypeEntity> messageTemplateTypeEntityList = this.messageTemplateTypeService.findAll();
        if (CollectionUtils.isEmpty(messageTemplateTypeEntityList)) {
            log.info("Sync message template. no message template type found.");
            return;
        }

        // 遍历当前所有的消息类型，逐一刷新消息内容模版
        for (MessageTypeEntity messageTypeEntity : messageTypeEntityList) {
            Long typeId = messageTypeEntity.getId();
            String typeCore = messageTypeEntity.getCode();

            log.info("Sync message template. type - [{}]. start.", typeCore);

            for (MessageTemplateTypeEntity messageTemplateTypeEntity : messageTemplateTypeEntityList) {
                Long templateTypeId = messageTemplateTypeEntity.getId();
                String templateTypeCode = messageTemplateTypeEntity.getCode();

                log.info("Sync message template. type - [{}]. template type - [{}]. start.", typeCore, templateTypeCode);

                MessageTemplateEntity messageTemplateEntity = this.messageTemplateService.findByType(typeId, templateTypeId);

                // 找不到消息模版记录，直接跳过
                if (messageTemplateEntity == null) {
                    log.info("Sync message template. type - [{}]. template type - [{}]. no template found..", typeCore, templateTypeCode);
                    continue;
                }

                // 当强制刷新或者模版内容为空时，读取模版文件，更新到数据库
                if (StringUtils.isEmpty(messageTemplateEntity.getContent()) || force) {
                    String content = "";
                    try {
                        // 获取模板文件
                        String path = (TPL_CLASSPATH + messageTypeEntity.getCode() + "/" + messageTemplateTypeEntity.getCode() + ".html").toLowerCase();
                        ClassPathResource classPathResource = new ClassPathResource(path);
                        content = IOUtils.toString(classPathResource.getInputStream(), GlobalConstants.ENCODING);
                    } catch (Exception e) {
                        log.error("Sync message template. type - [{}]. template type - [{}]. failed.", typeCore, templateTypeCode, e);
                    }

                    // 更新模版
                    messageTemplateEntity.setContent(content);
                    this.messageTemplateService.save(messageTemplateEntity);

                    log.info("Sync message template. type - [{}]. template type - [{}]. done.", typeCore, templateTypeCode);
                } else {
                    log.info("Sync message template. type - [{}]. template type - [{}]. not empty content. skip.", typeCore, templateTypeCode);
                }
            }
            log.info("Sync message template. type - [{}]. done.", typeCore);
        }

        this.messageTemplateTypeService.clearCache();
        this.messageTemplateService.clearCache();
        this.messageTypeService.clearCache();

        log.info("Sync message template. done.");
    }

    private MessageSender getMessageSender(MessageTemplateTypeEnum messageTemplateTypeEnum) {
        if (messageTemplateTypeEnum == null) {
            return null;
        }
        return switch (messageTemplateTypeEnum) {
            case NOTICE -> SpringUtils.getBean(MessageNoticeSender.class);
            case SMS -> SpringUtils.getBean(MessageSmsSender.class);
            case MAIL -> SpringUtils.getBean(MessageMailSender.class);
            case WECHAT -> SpringUtils.getBean(MessageWeChatSender.class);
            case WEWORK -> SpringUtils.getBean(MessageWeWorkSender.class);
            case LARK -> SpringUtils.getBean(MessageLarkSender.class);
            case DINGTALK -> SpringUtils.getBean(MessageDingTalkSender.class);
        };
    }

    /**
     * 辅助方法，用于获取系统用户的邮箱和手机号码等信息
     */
    private MessageUserEntity createMessageUser(Long messageId, MessageUserDto userDto) {
        MessageUserEntity.MessageUserEntityBuilder builder = MessageUserEntity.builder()
                .messageId(messageId)
                .typeId(userDto.getType().getValue())
                .userId((ObjectUtils.isEmpty(userDto.getUserId()) || userDto.getUserId() <= 0) ? 0L : userDto.getUserId());

        UserEntity userEntity = null;
        if (!ObjectUtils.isEmpty(userDto.getUserId()) && userDto.getUserId() > 0) {
            userEntity = this.userService.findCacheById(userDto.getUserId());
        }

        if (userEntity != null) {
            builder.username(StringUtils.isNotEmpty(userDto.getUsername()) ? userDto.getUsername() : userEntity.getUsername());
            builder.email(StringUtils.isNotEmpty(userDto.getEmail()) ? userDto.getEmail() : userEntity.getEmail());
            builder.mobileCountryCode(StringUtils.isNotEmpty(userDto.getMobileCountryCode()) ? userDto.getMobileCountryCode() : userEntity.getMobileCountryCode());
            builder.mobileNumber(StringUtils.isNotEmpty(userDto.getMobileNumber()) ? userDto.getMobileNumber() : userEntity.getMobileNumber());
        } else {
            builder.username(userDto.getUsername());
            builder.email(userDto.getEmail());
            builder.mobileCountryCode(userDto.getMobileCountryCode());
            builder.mobileNumber(userDto.getMobileNumber());
        }
        return builder.build();
    }

    @Autowired
    public void setHtmlTemplateService(HtmlTemplateService htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired

    public void setMessageTypeService(MessageTypeService messageTypeService) {
        this.messageTypeService = messageTypeService;
    }

    @Autowired
    public void setMessageUserService(MessageUserService messageUserService) {
        this.messageUserService = messageUserService;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

    @Autowired
    public void setMessageTemplateService(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    @Autowired
    public void setMessageTemplateTypeService(MessageTemplateTypeService messageTemplateTypeService) {
        this.messageTemplateTypeService = messageTemplateTypeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
