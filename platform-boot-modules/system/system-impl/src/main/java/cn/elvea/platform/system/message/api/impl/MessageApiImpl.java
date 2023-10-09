package cn.elvea.platform.system.message.api.impl;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.enums.BaseEnum;
import cn.elvea.platform.commons.core.extensions.template.HtmlTemplateService;
import cn.elvea.platform.commons.core.utils.*;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.service.UserService;
import cn.elvea.platform.system.message.api.MessageApi;
import cn.elvea.platform.system.message.enums.MessageStatusEnum;
import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cn.elvea.platform.system.message.enums.MessageUserTypeEnum;
import cn.elvea.platform.system.message.model.dto.CreateMessageDto;
import cn.elvea.platform.system.message.model.dto.MessageUserDto;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import cn.elvea.platform.system.message.model.entity.*;
import cn.elvea.platform.system.message.sender.*;
import cn.elvea.platform.system.message.service.*;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageApiImpl implements MessageApi {

    private final HtmlTemplateService htmlTemplateService;

    private final MessageService messageService;

    private final MessageTypeService messageTypeService;

    private final MessageUserService messageUserService;

    private final MessageContentService messageContentService;

    private final MessageTemplateService messageTemplateService;

    private final MessageTemplateTypeService messageTemplateTypeService;

    private final UserService userService;

    /**
     * @see MessageApi#createMessage(CreateMessageDto)
     */
    @Override
    public Long createMessage(CreateMessageDto message) throws Exception {
        // 获取消息类型
        MessageTypeEntity messageTypeEntity = null;
        if (StringUtils.isNotEmpty(message.getType())) {
            messageTypeEntity = messageTypeService.findByCode(message.getType());
        } else if (message.getTypeId() != null && message.getTypeId() > 0) {
            messageTypeEntity = messageTypeService.findById(message.getTypeId());
        }

        if (messageTypeEntity == null) {
            log.info("Invalid message type [{}][{}].", message.getType(), message.getTypeId());
            return 0L;
        }

        MessageEntity entity = MessageEntity.builder()
                .typeId(messageTypeEntity.getId())
                .subject(StringUtils.isNotEmpty(message.getSubject()) ? message.getSubject() : messageTypeEntity.getTitle())
                .content(message.getContent())
                .data(MapUtils.isNotEmpty(message.getParams()) ? JacksonUtils.toJson(message.getParams()) : "")
                .status(MessageStatusEnum.PENDING.getValue())
                .build();

        this.messageService.save(entity);

        Long messageId = entity.getId();

        // 处理消息用户
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

        // 处理消息内容
        // 如果指定了模版，那将沿用指定的模版来发送消息
        // 未指定的情况，则用系统已经保存并启用的模版来发送
        log.info("Handle message content [{}].", message.getType());

        List<MessageTemplateTypeEnum> templateTypeList = CollectionUtils.isEmpty(message.getTemplateTypeList()) ?
                Arrays.stream(MessageTemplateTypeEnum.values()).toList() : message.getTemplateTypeList();

        List<MessageContentEntity> contentEntityList = Lists.newArrayList();
        for (MessageTemplateTypeEnum templateType : templateTypeList) {
            log.info("Handle message content [{}] for template type [{}].", message.getType(), templateType.getCode());

            MessageTemplateTypeEntity templateTypeEntity = messageTemplateTypeService.findByCode(templateType.getCode());
            if (templateTypeEntity == null) {
                log.info("Invalid MessageTemplateType [{}].", templateType.getCode());
                continue;
            }
            if (!templateTypeEntity.isActiveEntity()) {
                log.info("Inactive MessageTemplateType [{}].", templateType.getCode());
                continue;
            }

            String content = "";
            if (StringUtils.isNotEmpty(message.getContent())) {
                log.info("Handle message content [{}] for template type [{}]. use content.", message.getType(), templateType.getCode());
                content = message.getContent();
            } else if (StringUtils.isNotEmpty(message.getTemplate())) {
                log.info("Handle message content [{}] for template type [{}]. use template.", message.getType(), templateType.getCode());

                content = htmlTemplateService.toHtml(message.getTemplate(), message.getParams());
            } else {
                MessageTemplateEntity templateEntity = this.messageTemplateService.findByType(messageTypeEntity.getId(), templateTypeEntity.getId());
                if (templateEntity == null) {
                    log.info("Invalid MessageTemplate typeId [{}] templateTypeId [{}].", messageTypeEntity.getId(), templateTypeEntity.getId());
                    continue;
                }
                if (!templateEntity.isActiveEntity()) {
                    log.info("Inactive MessageTemplate typeId [{}] templateTypeId [{}].", messageTypeEntity.getId(), templateTypeEntity.getId());
                    continue;
                }

                log.info("Handle message content [{}] for template type [{}]. use system template.", message.getType(), templateType.getCode());

                content = htmlTemplateService.toHtml(templateEntity.getContent(), message.getParams());
            }
            MessageContentEntity contentEntity = MessageContentEntity.builder()
                    .messageId(entity.getId())
                    .templateTypeId(templateTypeEntity.getId())
                    .content(content)
                    .build();
            contentEntityList.add(contentEntity);
        }

        if (CollectionUtils.isEmpty(contentEntityList)) {
            log.info("Handle message content [{}]. empty.", message.getType());
        } else {
            log.info("Handle message content [{}]. save.", message.getType());
            this.messageContentService.saveBatch(contentEntityList);
        }

        return messageId;
    }

    /**
     * @see MessageApi#sendMessage()
     */
    @Override
    public void sendMessage() throws Exception {
        log.info("sendMessage start...");
        List<MessageEntity> messageEntityList = this.messageService.findByStatus(Collections.singletonList(MessageStatusEnum.PENDING));
        if (CollectionUtils.isNotEmpty(messageEntityList)) {
            log.info("sendMessage. {} messages found.", messageEntityList.size());
            for (MessageEntity messageEntity : messageEntityList) {
                try {
                    log.info("sendMessage. message [{}] send...", messageEntity.getId());
                    messageEntity.setStatus(MessageStatusEnum.SENDING.getValue());
                    this.messageService.save(messageEntity);
                    log.info("sendMessage. message [{}] sent successfully.", messageEntity.getId());
                } catch (Exception e) {
                    log.info("sendMessage. message [{}] sent failed.", messageEntity.getId(), e);
                }
            }
        } else {
            log.info("sendMessage. no message found.");
        }
        log.info("sendMessage done.");
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

            MessageTemplateTypeEnum messageTemplateTypeEnum = BaseEnum.getEnumByValue(messageContentEntity.getTemplateTypeId(), MessageTemplateTypeEnum.class);
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

        List<MessageTypeEntity> messageTypeEntityList = this.messageTypeService.findAll();
        if (CollectionUtils.isEmpty(messageTypeEntityList)) {
            log.info("Empty Message Type.");
            return;
        }

        for (MessageTypeEntity messageTypeEntity : messageTypeEntityList) {
            log.info("Sync message template for type [{}]. start.", messageTypeEntity.getCode());
            List<MessageTemplateEntity> messageTemplateEntityList = this.messageTemplateService.findAll();
            if (CollectionUtils.isEmpty(messageTemplateEntityList)) {
                log.info("Sync message template for type [{}]. empty template.", messageTypeEntity.getCode());
                continue;
            }
            for (MessageTemplateEntity messageTemplateEntity : messageTemplateEntityList) {
                MessageTemplateTypeEntity messageTemplateTypeEntity = this.messageTemplateTypeService.findById(messageTemplateEntity.getTypeId());
                if (messageTemplateTypeEntity == null) {
                    continue;
                }
                log.info("Sync message template for type [{}] template [{}]. start.", messageTypeEntity.getCode(), messageTemplateTypeEntity.getCode());
                if (StringUtils.isEmpty(messageTemplateEntity.getContent()) || force) {
                    String content = "";
                    try {
                        // 获取模板文件
                        ClassPathResource classPathResource = new ClassPathResource("/template/message/" + messageTypeEntity.getCode() + "__" + messageTemplateTypeEntity.getCode() + ".html");
                        content = IOUtils.toString(classPathResource.getInputStream(), GlobalConstants.ENCODING);
                    } catch (Exception e) {
                        log.error("Sync message template for type [{}] template [{}]. failed to load template.", messageTypeEntity.getCode(), messageTemplateTypeEntity.getCode(), e);
                    }
                    messageTemplateEntity.setContent(content);
                    this.messageTemplateService.save(messageTemplateEntity);
                }
                log.info("Sync message template for type [{}] template [{}]. done.", messageTypeEntity.getCode(), messageTemplateTypeEntity.getCode());
            }
            log.info("Sync message template for type [{}]. done.", messageTypeEntity.getCode());
        }

        log.info("Sync message template done.");
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
            builder.email(StringUtils.isNotEmpty(userDto.getEmail()) ? userDto.getEmail() : userEntity.getEmail());
            builder.mobileCountryCode(StringUtils.isNotEmpty(userDto.getMobileCountryCode()) ? userDto.getMobileCountryCode() : userEntity.getMobileCountryCode());
            builder.mobileNumber(StringUtils.isNotEmpty(userDto.getMobileNumber()) ? userDto.getMobileNumber() : userEntity.getMobileNumber());
        } else {
            builder.email(userDto.getEmail());
            builder.mobileCountryCode(userDto.getMobileCountryCode());
            builder.mobileNumber(userDto.getMobileNumber());
        }
        return builder.build();
    }

}
