package cc.elvea.platform.system.message.api;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.utils.DateTimeUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.commons.constants.SystemMessageConstants;
import cc.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cc.elvea.platform.system.message.model.dto.CreateMessageDto;
import cc.elvea.platform.system.message.utils.MessageBuilder;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author elvea
 */
public class MessageApiTests extends BaseTests {

    @Autowired
    private MessageApi messageApi;

    @Test
    public void directMailTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.MAIL)
                .content("测试消息内容 - " + DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.FULL_DATE_TIME))
                .sender(1L)
                .recipient("huang@elvea.cn")
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void tplMailTest() throws Exception {
        this.messageApi.syncTemplate(true);
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", StringUtils.randomNumeric(6));
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                .templateType(MessageTemplateTypeEnum.MAIL)
                .sender(1L)
                .recipient("me@elvea.cn")
                .params(params)
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void baseTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.CAPTCHA_MESSAGE)
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
                .type(SystemMessageConstants.CAPTCHA_MESSAGE)
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
    public void smsMessageTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.TEST_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.SMS)
                .content("测试消息内容")
                .sender(1L)
                .recipientByUsername("dev")
                .build();
        Long id = this.messageApi.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageApi.sendMessage(id);
    }

    @Test
    public void weWorkMessageTest() throws Exception {
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.TEST_MESSAGE)
                .subject("测试消息")
                .templateType(MessageTemplateTypeEnum.WEWORK)
                .content("测试消息内容")
                .sender(1L)
                .recipientByUsername("dev")
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
