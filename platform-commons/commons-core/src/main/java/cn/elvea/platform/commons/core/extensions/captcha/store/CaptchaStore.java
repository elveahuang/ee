package cn.elvea.platform.commons.core.extensions.captcha.store;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaStore {

    String get(String key);

    void set(String key, String value, Duration duration);

}
