package cc.elvea.platform.system.commons.manager;

import cc.elvea.platform.commons.extensions.captcha.Captcha;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 */
public interface CaptchaManager {

    Captcha generate(CaptchaRequest request) throws Exception;

    boolean check(CaptchaCheckRequest request);

}
