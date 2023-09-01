package cn.elvea.platform.commons.core.extensions.mail;

import cn.elvea.platform.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class MailSenderTests extends BaseTests {

    @Autowired
    private MailSender mailSender;

    @Test
    public void base() throws Exception {
        MailBody body = MailBody.builder().subject("Test").content("Java Mail Sender.").to("elvea.huang@gmail.com").build();
        mailSender.send(body);
    }

}
