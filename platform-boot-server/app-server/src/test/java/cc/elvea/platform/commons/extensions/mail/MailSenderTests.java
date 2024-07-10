package cc.elvea.platform.commons.extensions.mail;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.mail.MailBody;
import cc.elvea.platform.commons.mail.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
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
