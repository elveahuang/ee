package cn.elvea.platform.commons.core.extensions.captcha.service;

import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaService {

    Captcha generate(CaptchaRequest request) throws Exception;

    boolean check(CaptchaCheckRequest request);

    CaptchaProvider getCaptchaProvider(CaptchaRequest request);

}
