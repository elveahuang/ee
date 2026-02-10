package cc.wdev.platform.commons.core.mail;

import cc.wdev.platform.commons.core.mail.domain.MailBody;
import cc.wdev.platform.commons.oapis.sms.SmsResult;

/**
 * @author elvea
 */
public interface MailSender {

    SmsResult send(MailBody body) throws Exception;

}
