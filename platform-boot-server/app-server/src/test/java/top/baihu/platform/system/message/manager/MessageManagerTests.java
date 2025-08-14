package top.baihu.platform.system.message.manager;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.constants.DateTimeConstants;
import top.baihu.platform.commons.utils.DateTimeUtils;
import top.baihu.platform.commons.utils.StringUtils;
import top.baihu.platform.system.commons.constants.SystemMessageConstants;
import top.baihu.platform.system.message.domain.dto.CreateMessageDto;
import top.baihu.platform.system.message.enums.MessageTemplateTypeEnum;
import top.baihu.platform.system.message.utils.MessageBuilder;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author elvea
 */
public class MessageManagerTests extends BaseTests {

    @Autowired
    private MessageManager messageManager;

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
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
    }

    @Test
    public void tplMailTest() throws Exception {
        this.messageManager.syncTemplate(true);
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", StringUtils.randomNumeric(6));
        CreateMessageDto message = MessageBuilder.builder()
            .type(SystemMessageConstants.CAPTCHA_MESSAGE)
            .templateType(MessageTemplateTypeEnum.MAIL)
            .sender(1L)
            .recipient("me@elvea.cn")
            .params(params)
            .build();
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
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
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
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
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
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
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
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
        Long id = this.messageManager.createMessage(message);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 0);
        this.messageManager.sendMessage(id);
    }

    @Test
    public void sendMessageTest() throws Exception {
        this.messageManager.sendMessage();
    }

    @Test
    public void initTemplateTest() throws Exception {
        this.messageManager.initTemplate();
    }

    @Test
    public void syncTemplateTest() throws Exception {
        this.messageManager.syncTemplate(true);
    }

}
