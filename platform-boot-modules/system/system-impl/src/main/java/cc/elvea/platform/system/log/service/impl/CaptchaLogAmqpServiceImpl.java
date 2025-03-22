package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.commons.message.rabbit.AbstractAmqpService;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cc.elvea.platform.system.log.model.converter.CaptchaLogConverter;
import cc.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import cc.elvea.platform.system.log.service.CaptchaLogAmqpService;
import cc.elvea.platform.system.log.service.CaptchaLogService;
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
public class CaptchaLogAmqpServiceImpl extends AbstractAmqpService<CaptchaLogDto> implements CaptchaLogAmqpService {

    private final CaptchaLogService captchaLogService;

    public CaptchaLogAmqpServiceImpl(CaptchaLogService captchaLogService) {
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
