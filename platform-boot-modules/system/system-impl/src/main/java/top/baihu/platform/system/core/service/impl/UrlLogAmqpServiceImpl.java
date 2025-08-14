package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.message.rabbit.AbstractAmqpService;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.commons.constants.SystemAmqpConstants;
import top.baihu.platform.system.core.domain.converter.UrlLogConverter;
import top.baihu.platform.system.core.domain.entity.UrlLogEntity;
import top.baihu.platform.system.core.service.UrlLogAmqpService;
import top.baihu.platform.system.core.service.UrlLogService;

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
