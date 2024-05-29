package cc.elvea.platform.commons.extensions.captcha.store;

import cc.elvea.platform.commons.extensions.captcha.Captcha;

import java.time.Duration;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaStore {

    Captcha get(String key);

    void set(String key, Captcha value, Duration duration);

    void remove(String key);

}
