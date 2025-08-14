package top.baihu.platform.system.core.manager;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.enums.CaptchaTypeEnum;
import top.baihu.platform.commons.extensions.captcha.Captcha;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaRequest;
import top.baihu.platform.commons.extensions.captcha.service.CaptchaService;
import top.baihu.platform.system.commons.constants.SystemMessageConstants;
import top.baihu.platform.system.message.domain.dto.CreateMessageDto;
import top.baihu.platform.system.message.enums.MessageTargetTypeEnum;
import top.baihu.platform.system.message.enums.MessageTemplateTypeEnum;
import top.baihu.platform.system.message.manager.MessageManager;
import top.baihu.platform.system.message.utils.MessageBuilder;

import java.util.Map;

/**
 * @author elvea
 */
@Service
public class CaptchaManagerImpl implements CaptchaManager {

    private CaptchaService captchaService;

    private MessageManager messageManager;

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
            this.messageManager.createMessage(message);
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
            this.messageManager.createMessage(message);
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
    public void setMessageApi(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

}
