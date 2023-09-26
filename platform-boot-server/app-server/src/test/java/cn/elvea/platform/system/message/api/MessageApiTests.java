package cn.elvea.platform.system.message.api;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.system.commons.constants.SystemMessageConstants;
import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cn.elvea.platform.system.message.model.dto.CreateMessageDto;
import cn.elvea.platform.system.message.utils.MessageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
public class MessageApiTests extends BaseTests {

    @Autowired
    MessageApi messageApi;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(messageApi);
        CreateMessageDto message = MessageBuilder.builder()
                .type(SystemMessageConstants.REGISTER)
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

}
