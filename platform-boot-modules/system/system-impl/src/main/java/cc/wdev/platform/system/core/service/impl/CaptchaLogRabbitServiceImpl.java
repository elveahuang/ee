package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.commons.message.rabbit.AbstractRabbitService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.core.domain.converter.CaptchaLogConverter;
import cc.wdev.platform.system.core.domain.entity.CaptchaLogEntity;
import cc.wdev.platform.system.core.service.CaptchaLogRabbitService;
import cc.wdev.platform.system.core.service.CaptchaLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see CaptchaLogService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.CAPTCHA_LOG_QUEUE)
public class CaptchaLogRabbitServiceImpl extends AbstractRabbitService<CaptchaLogDto> implements CaptchaLogRabbitService {

    private final CaptchaLogService captchaLogService;

    public CaptchaLogRabbitServiceImpl(CaptchaLogService captchaLogService) {
        this.captchaLogService = captchaLogService;
    }

    @Override
    public void execute(CaptchaLogDto dto) {
        CaptchaLogEntity entity = SpringUtils.getBean(CaptchaLogConverter.class).dto2Entity(dto);
        this.captchaLogService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.CAPTCHA_LOG_QUEUE;
    }

}
