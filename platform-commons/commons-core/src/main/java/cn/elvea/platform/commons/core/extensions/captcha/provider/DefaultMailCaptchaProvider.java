package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.extensions.mail.MailBody;
import cn.elvea.platform.commons.core.extensions.mail.MailSender;
import cn.elvea.platform.commons.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DefaultMailCaptchaProvider extends AbstractCaptchaProvider implements MailCaptchaProvider {

    private final MailSender mailSender;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        String uuid = StringUtils.uuid();
        String number = StringUtils.randomNumeric(6);
        Captcha captcha = Captcha.builder().type(CaptchaTypeEnum.MAIL).key(uuid).value(number).build();
        MailBody body = MailBody.builder().subject("邮件验证码").content(number).to(request.getEmail()).build();
        mailSender.send(body);
        return captcha;
    }

}
