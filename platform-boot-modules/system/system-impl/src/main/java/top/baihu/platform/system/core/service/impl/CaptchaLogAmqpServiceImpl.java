package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import top.baihu.platform.commons.message.rabbit.AbstractAmqpService;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.commons.constants.SystemAmqpConstants;
import top.baihu.platform.system.core.domain.converter.CaptchaLogConverter;
import top.baihu.platform.system.core.domain.entity.CaptchaLogEntity;
import top.baihu.platform.system.core.service.CaptchaLogAmqpService;
import top.baihu.platform.system.core.service.CaptchaLogService;

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
