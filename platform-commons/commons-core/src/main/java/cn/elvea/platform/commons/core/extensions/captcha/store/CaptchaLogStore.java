package cn.elvea.platform.commons.core.extensions.captcha.store;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaLogStore {

    void saveCaptchaLog(CaptchaLogDto captchaLog);

}
