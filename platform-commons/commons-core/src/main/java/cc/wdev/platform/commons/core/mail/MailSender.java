package cc.wdev.platform.commons.core.mail;

import cc.wdev.platform.commons.core.mail.domain.MailBody;

/**
 * @author elvea
 */
public interface MailSender {

    MailResult send(MailBody body) throws Exception;

}
