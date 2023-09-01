package cn.elvea.platform.commons.core.extensions.sms;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface SmsSender {

    void send(SmsBody body) throws Exception;

    void send(SmsServer config, SmsBody body) throws Exception;

}
