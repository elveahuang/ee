package top.baihu.platform.commons.core.mail;

import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.core.mail.spring.SpringMailSender;

/**
 * @author elvea
 */
@Slf4j
public record MailFactory(MailConfig config) {

    public MailSender getMailSender() {
        return getMailSender(this.config);
    }

    public MailSender getMailSender(MailConfig config) {
        return new SpringMailSender(config);
    }

}
