package top.baihu.platform.commons.core.mail;

import top.baihu.platform.commons.core.mail.domain.MailBody;

/**
 * @author elvea
 */
public interface MailSender {

    void send(MailBody body) throws Exception;

}
