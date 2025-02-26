package cc.elvea.platform.system.commons.api.impl;

import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import cc.elvea.platform.commons.extensions.captcha.Captcha;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;
import cc.elvea.platform.commons.extensions.captcha.service.CaptchaService;
import cc.elvea.platform.system.commons.api.CaptchaApi;
import cc.elvea.platform.system.commons.constants.SystemMessageConstants;
import cc.elvea.platform.system.message.api.MessageApi;
import cc.elvea.platform.system.message.enums.MessageTargetTypeEnum;
import cc.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cc.elvea.platform.system.message.model.dto.CreateMessageDto;
import cc.elvea.platform.system.message.utils.MessageBuilder;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author elvea
 */
@Service
public class CaptchaApiImpl implements CaptchaApi {

    private CaptchaService captchaService;

    private MessageApi messageApi;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        Captcha captcha = this.captchaService.generate(request);
        if (CaptchaTypeEnum.SMS.equals(captcha.getType())) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("code", captcha.getValue());

            CreateMessageDto message = MessageBuilder.builder()
                    .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                    .templateType(MessageTemplateTypeEnum.SMS)
                    .targetType(MessageTargetTypeEnum.IMMEDIATE)
                    .sender(1L)
                    .recipient(request.getMobileCountryCode(), request.getMobileNumber())
                    .params(params)
                    .build();
            this.messageApi.createMessage(message);
        } else if (CaptchaTypeEnum.MAIL.equals(captcha.getType())) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("code", captcha.getValue());
            CreateMessageDto message = MessageBuilder.builder()
                    .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                    .templateType(MessageTemplateTypeEnum.MAIL)
                    .targetType(MessageTargetTypeEnum.IMMEDIATE)
                    .sender(1L)
                    .recipient(request.getEmail())
                    .params(params)
                    .build();
            this.messageApi.createMessage(message);
        }
        return captcha;
    }

    @Override
    public boolean check(CaptchaCheckRequest request) {
        return this.captchaService.check(request);
    }

    @Autowired
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Autowired
    public void setMessageApi(MessageApi messageApi) {
        this.messageApi = messageApi;
    }

}
