package cc.elvea.platform.commons.extensions.captcha.provider;

import cc.elvea.platform.commons.extensions.captcha.Captcha;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 */
public interface CaptchaProvider {

    Captcha generate(CaptchaRequest request) throws Exception;

}
