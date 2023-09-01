package cn.elvea.platform.commons.core.extensions.mail;

import jakarta.mail.MessagingException;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MailSender {

    void send(MailBody body) throws MessagingException;

    void send(MailServer config, MailBody body) throws MessagingException;

}
