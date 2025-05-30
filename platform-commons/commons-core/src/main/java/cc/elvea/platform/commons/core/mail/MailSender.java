package cc.elvea.platform.commons.core.mail;

import cc.elvea.platform.commons.core.mail.model.MailBody;

/**
 * @author elvea
 */
public interface MailSender {

    void send(MailBody body) throws Exception;

}
