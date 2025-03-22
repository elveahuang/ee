package cc.elvea.platform.commons.extensions.captcha.store;

import cc.elvea.platform.commons.extensions.captcha.Captcha;

import java.time.Duration;

/**
 * @author elvea
 */
public interface CaptchaStore {

    Captcha get(String key);

    void set(String key, Captcha value, Duration duration);

    void remove(String key);

}
