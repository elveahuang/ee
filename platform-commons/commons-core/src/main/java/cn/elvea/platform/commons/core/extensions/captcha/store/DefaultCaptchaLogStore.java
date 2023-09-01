package cn.elvea.platform.commons.core.extensions.captcha.store;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class DefaultCaptchaLogStore implements CaptchaLogStore {

    @Override
    public void saveCaptchaLog(CaptchaLogDto captchaLog) {
        log.info(captchaLog.toString());
    }

}
