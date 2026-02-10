package cc.wdev.platform.commons.oapis.sms;

import cc.wdev.platform.commons.oapis.sms.domain.SmsBody;

/**
 * @author elvea
 */
public interface SmsSender<C, R> {

    R send(SmsBody body) throws Exception;

    R send(C config, SmsBody body) throws Exception;

}
