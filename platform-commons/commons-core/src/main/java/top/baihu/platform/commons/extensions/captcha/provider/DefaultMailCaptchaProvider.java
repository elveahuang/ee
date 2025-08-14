package top.baihu.platform.commons.extensions.captcha.provider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.enums.CaptchaTypeEnum;
import top.baihu.platform.commons.extensions.captcha.Captcha;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaRequest;
import top.baihu.platform.commons.utils.StringUtils;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class DefaultMailCaptchaProvider implements MailCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        int length = (request.getSize() <= 0) ? 6 : request.getSize();
        String number = StringUtils.randomNumeric(length);
        return Captcha.builder()
            .type(CaptchaTypeEnum.MAIL)
            .email(request.getEmail())
            .key(StringUtils.uuid())
            .value(number).build();
    }

}
