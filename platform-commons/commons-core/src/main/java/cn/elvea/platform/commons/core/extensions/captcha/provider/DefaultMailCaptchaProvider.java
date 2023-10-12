package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DefaultMailCaptchaProvider implements MailCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        String uuid = StringUtils.uuid();
        String number = StringUtils.randomNumeric((request.getSize() <= 0 || request.getSize() >= 8) ? 6 : request.getSize());
        return Captcha.builder().type(CaptchaTypeEnum.MAIL).key(uuid).value(number).build();
    }

}
