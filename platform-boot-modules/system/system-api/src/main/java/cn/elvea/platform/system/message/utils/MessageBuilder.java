package cn.elvea.platform.system.message.utils;

import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cn.elvea.platform.system.message.enums.MessageUserTypeEnum;
import cn.elvea.platform.system.message.model.dto.CreateMessageDto;
import cn.elvea.platform.system.message.model.dto.MessageUserDto;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class MessageBuilder {

    /**
     * 消息标题
     */
    private String subject;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息模版
     */
    private String template;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 发件人
     */
    private MessageUserDto sender;

    /**
     * 收件人
     */
    private List<MessageUserDto> recipients = Lists.newArrayList();

    /**
     * 指定模版
     */
    private List<MessageTemplateTypeEnum> templateTypeList = Lists.newArrayList();

    /**
     * 目标发送时间
     */
    private LocalDateTime targetSentDatetime;

    private MessageBuilder() {
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public MessageBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MessageBuilder content(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder template(String template) {
        this.template = template;
        return this;
    }

    public MessageBuilder type(String type) {
        this.type = type;
        return this;
    }

    public MessageBuilder sender(Long userId) {
        this.sender = new MessageUserDto(MessageUserTypeEnum.FROM, userId);
        return this;
    }

    public MessageBuilder recipient(Long userId) {
        this.recipients.add(new MessageUserDto(MessageUserTypeEnum.TO, userId));
        return this;
    }

    public MessageBuilder recipient(List<Long> recipientIds) {
        if (!CollectionUtils.isEmpty(recipientIds)) {
            for (Long userId : recipientIds) {
                this.recipients.add(new MessageUserDto(MessageUserTypeEnum.TO, userId));
            }
        }
        return this;
    }

    public MessageBuilder recipient(String email) {
        this.recipients.add(new MessageUserDto(MessageUserTypeEnum.TO, email));
        return this;
    }

    public MessageBuilder recipient(String mobileCountryCode, String mobileNumber) {
        this.recipients.add(new MessageUserDto(MessageUserTypeEnum.TO, mobileCountryCode, mobileNumber));
        return this;
    }

    public MessageBuilder recipientByUsername(String username) {
        this.recipients.add(MessageUserDto.builder().type(MessageUserTypeEnum.TO).username(username).build());
        return this;
    }

    public MessageBuilder templateType(MessageTemplateTypeEnum templateType) {
        this.templateTypeList.add(templateType);
        return this;
    }

    public MessageBuilder targetSentDatetime(LocalDateTime targetSentDatetime) {
        this.targetSentDatetime = targetSentDatetime;
        return this;
    }

    public CreateMessageDto build() {
        CreateMessageDto message = new CreateMessageDto();
        message.setSubject(this.subject);
        message.setContent(this.content);
        message.setTemplate(this.template);
        message.setType(this.type);
        message.setTargetSentDatetime(this.targetSentDatetime);
        message.setSender(this.sender);
        message.setRecipients(this.recipients);
        message.setTemplateTypeList(this.templateTypeList);
        return message;
    }

}
