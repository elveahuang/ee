package top.baihu.platform.system.core.manager;

import top.baihu.platform.commons.extensions.captcha.Captcha;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 */
public interface CaptchaManager {

    Captcha generate(CaptchaRequest request) throws Exception;

    boolean check(CaptchaCheckRequest request);

}
