package cn.elvea.platform.commons.core.extensions.mail;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MailSender {

    void send(MailBody body) throws Exception;

    void send(MailServer config, MailBody body) throws Exception;

}
