package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.message.rabbit.AbstractRabbitService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.OperationLogConverter;
import cc.wdev.platform.system.core.domain.entity.OperationLogEntity;
import cc.wdev.platform.system.core.service.OperationLogRabbitService;
import cc.wdev.platform.system.core.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.wdev.platform.system.commons.constants.SystemAmqpConstants.OPERATION_LOG_QUEUE;

/**
 * @author elvea
 * @see OperationLogService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@Transactional
@RabbitListener(queues = OPERATION_LOG_QUEUE)
public class OperationLogRabbitServiceImpl extends AbstractRabbitService<OperationLogDto> implements OperationLogRabbitService {

    private final OperationLogService operationLogService;

    public OperationLogRabbitServiceImpl(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Override
    public void execute(OperationLogDto dto) {
        OperationLogEntity entity = SpringUtils.getBean(OperationLogConverter.class).dto2Entity(dto);
        this.operationLogService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return OPERATION_LOG_QUEUE;
    }

}
