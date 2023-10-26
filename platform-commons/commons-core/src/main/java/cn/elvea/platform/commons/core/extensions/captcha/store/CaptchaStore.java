package cn.elvea.platform.commons.core.extensions.captcha.store;

import cn.elvea.platform.commons.core.extensions.captcha.Captcha;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaStore {

    Captcha get(String key);

    void set(String key, Captcha value, Duration duration);

    void remove(String key);

}
