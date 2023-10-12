package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

/**
 * @author elvea
 * @since 0.0.1
 */
public class DefaultCodeCaptchaProvider implements CodeCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) {
        String uuid = StringUtils.uuid();
        int length = (request.getSize() <= 0 || request.getSize() >= 8) ? 6 : request.getSize();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, length, 64);
        lineCaptcha.getImageBase64();
        return Captcha.builder().type(CaptchaTypeEnum.CODE)
                .key(uuid)
                .value(lineCaptcha.getCode())
                .image(lineCaptcha.getImageBase64Data())
                .build();
    }

}
