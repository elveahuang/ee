package cn.elvea.platform.commons.core.extensions.captcha.service;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaService {

    Captcha generate(CaptchaRequest request) throws Exception;

    Boolean check(CaptchaTypeEnum captchaType);

    CaptchaProvider getCaptchaProvider(CaptchaRequest request);

}
