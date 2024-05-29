package cc.elvea.platform.commons.extensions.captcha.provider;

import cc.elvea.platform.commons.extensions.captcha.Captcha;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaProvider {

    Captcha generate(CaptchaRequest request) throws Exception;

}
