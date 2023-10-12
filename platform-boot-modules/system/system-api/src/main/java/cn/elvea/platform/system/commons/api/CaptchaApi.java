package cn.elvea.platform.system.commons.api;

import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CaptchaApi {

    Captcha generate(CaptchaRequest request) throws Exception;

}
