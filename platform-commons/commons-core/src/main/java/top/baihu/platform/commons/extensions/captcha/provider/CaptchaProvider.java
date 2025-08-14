package top.baihu.platform.commons.extensions.captcha.provider;

import top.baihu.platform.commons.extensions.captcha.Captcha;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 */
public interface CaptchaProvider {

    Captcha generate(CaptchaRequest request) throws Exception;

}
