package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.message.rabbit.AbstractAmqpService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.core.domain.converter.UrlLogConverter;
import cc.wdev.platform.system.core.domain.entity.UrlLogEntity;
import cc.wdev.platform.system.core.service.UrlLogAmqpService;
import cc.wdev.platform.system.core.service.UrlLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UrlLogAmqpService
 * @see AbstractAmqpService
 */
@Slf4j
@Service
@AllArgsConstructor
@RabbitListener(queues = SystemAmqpConstants.URL_LOG_QUEUE)
public class UrlLogAmqpServiceImpl extends AbstractAmqpService<UrlLogDto> implements UrlLogAmqpService {

    private final UrlLogService urlLogService;

    @Override
    public void execute(UrlLogDto dto) {
        UrlLogEntity entity = SpringUtils.getBean(UrlLogConverter.class).dto2Entity(dto);
        this.urlLogService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.URL_LOG_QUEUE;
    }

}
