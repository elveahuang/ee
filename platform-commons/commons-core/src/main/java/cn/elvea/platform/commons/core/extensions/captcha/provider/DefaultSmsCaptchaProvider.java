package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.extensions.sms.SmsBody;
import cn.elvea.platform.commons.core.extensions.sms.SmsSender;
import cn.elvea.platform.commons.core.utils.StringUtils;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DefaultSmsCaptchaProvider extends AbstractCaptchaProvider implements SmsCaptchaProvider {

    private final SmsSender smsSender;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        String uuid = StringUtils.uuid();
        String number = StringUtils.randomNumeric(6);
        Captcha captcha = Captcha.builder().type(CaptchaTypeEnum.SMS).key(uuid).value(number).build();
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", number);
        SmsBody body = SmsBody.builder()
                .mobileCountryCode(request.getMobileCountryCode())
                .mobileNumber(request.getMobileNumber())
                .params(params)
                .build();
        smsSender.send(body);
        return captcha;
    }

}
