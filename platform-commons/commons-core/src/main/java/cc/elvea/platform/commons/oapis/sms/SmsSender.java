package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.commons.oapis.sms.domain.SmsBody;

/**
 * @author elvea
 */
public interface SmsSender<C> {

    void send(SmsBody body) throws Exception;

    void send(C config, SmsBody body) throws Exception;

}
