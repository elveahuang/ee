package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.message.rabbit.AbstractAmqpService;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cc.elvea.platform.system.log.model.converter.UrlLogConverter;
import cc.elvea.platform.system.log.model.entity.UrlLogEntity;
import cc.elvea.platform.system.log.service.UrlLogAmqpService;
import cc.elvea.platform.system.log.service.UrlLogService;
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
