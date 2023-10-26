package cn.elvea.platform.commons.core.extensions.captcha.service;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CodeCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.MailCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.SmsCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.extensions.captcha.store.CaptchaStore;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultCaptchaService implements CaptchaService {

    private MailCaptchaProvider mailCaptchaProvider;

    private SmsCaptchaProvider smsCaptchaProvider;

    private CodeCaptchaProvider codeCaptchaProvider;

    private CaptchaStore captchaStore;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        Captcha captcha = getCaptchaProvider(request).generate(request);
        captchaStore.set(captcha.getKey(), captcha, request.getDuration());
        return captcha;
    }

    @Override
    public boolean check(CaptchaCheckRequest request) {
        Captcha captcha = captchaStore.get(request.getKey());

        boolean result = false;
        if (CaptchaTypeEnum.MAIL.equals(request.getType())) {
            result = (!ObjectUtils.isEmpty(captcha)
                    && captcha.getEmail().equalsIgnoreCase(request.getEmail())
                    && captcha.getValue().equalsIgnoreCase(request.getValue()));
        } else if (CaptchaTypeEnum.SMS.equals(request.getType())) {
            result = (!ObjectUtils.isEmpty(captcha)
                    && captcha.getMobileNumber().equalsIgnoreCase(request.getMobileNumber())
                    && captcha.getValue().equalsIgnoreCase(request.getValue()));
        } else if (CaptchaTypeEnum.CODE.equals(request.getType())) {
            result = (!ObjectUtils.isEmpty(captcha) && captcha.getValue().equalsIgnoreCase(request.getValue()));
        }

        // 校验后删除验证码
        if (request.isClearAfterCheck()) {
            captchaStore.remove(request.getKey());
        }

        return result;
    }

    @Override
    public CaptchaProvider getCaptchaProvider(CaptchaRequest request) {
        if (CaptchaTypeEnum.SMS.equals(request.getType())) {
            return this.smsCaptchaProvider;
        } else if (CaptchaTypeEnum.MAIL.equals(request.getType())) {
            return this.mailCaptchaProvider;
        } else if (CaptchaTypeEnum.CODE.equals(request.getType())) {
            return this.codeCaptchaProvider;
        }
        return this.codeCaptchaProvider;
    }

}
