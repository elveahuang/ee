package cn.elvea.platform.commons.core.extensions.captcha.service;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.CodeCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.MailCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.provider.SmsCaptchaProvider;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.extensions.captcha.store.CaptchaLogStore;
import cn.elvea.platform.commons.core.extensions.captcha.store.CaptchaStore;
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

    private CaptchaLogStore captchaLogStore;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        Captcha captcha = getCaptchaProvider(request).generate(request);
        captchaStore.set(captcha.getKey(), captcha.getValue(), request.getDuration());
        if (this.captchaLogStore != null) {
            CaptchaLogDto captchaLog = CaptchaLogDto.builder()
                    .email(request.getEmail())
                    .mobileCountryCode(request.getMobileCountryCode())
                    .mobileNumber(request.getMobileNumber())
                    .captchaType(captcha.getKey())
                    .captchaKey(captcha.getValue())
                    .captchaValue(captcha.getType().getCode())
                    .build();
            this.captchaLogStore.saveCaptchaLog(captchaLog);
        }
        return captcha;
    }

    @Override
    public Boolean check(CaptchaTypeEnum captchaType) {
        return null;
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
