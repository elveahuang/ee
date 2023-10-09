package cn.elvea.platform.system.message.api;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.utils.DateTimeUtils;
import cn.elvea.platform.system.commons.constants.SystemMessageConstants;
import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cn.elvea.platform.system.message.model.dto.CreateMessageDto;
import cn.elvea.platform.system.message.utils.MessageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
public class MessageApiTests extends BaseTests {

    @Autowired
    private MessageApi messageApi;

    @Test
    public void directMailTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.REGISTER_CAPTCHA_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.MAIL)
                .content("测试消息内容 - " + DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.FULL_DATE_TIME))
                .sender(1L)
                .recipient("elvea.huang@gmail.com")
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void baseTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.REGISTER_CAPTCHA_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.MAIL)
                .content("测试消息内容")
                .sender(1L)
                .recipient(1L)
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void fullTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.REGISTER_CAPTCHA_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.MAIL)
                .templateType(MessageTemplateTypeEnum.SMS)
                .templateType(MessageTemplateTypeEnum.NOTICE)
                .content("测试消息内容")
                .sender(1L)
                .recipient(1L)
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void weWorkMessageTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.REGISTER_CAPTCHA_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.WEWORK)
                .content("测试消息内容")
                .sender(1L)
                .recipientByUsername("elvea")
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void sendMessageTest() throws Exception {
        this.messageApi.sendMessage();
    }

    @Test
    public void initTemplateTest() throws Exception {
        this.messageApi.initTemplate();
    }

    @Test
    public void syncTemplateTest() throws Exception {
        this.messageApi.syncTemplate(true);
    }

}
