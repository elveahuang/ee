package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.message.rabbit.AbstractAmqpService;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.log.model.converter.OperationLogConverter;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import cc.elvea.platform.system.log.service.OperationLogAmqpService;
import cc.elvea.platform.system.log.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.elvea.platform.system.commons.constants.SystemAmqpConstants.OPERATION_LOG_QUEUE;

/**
 * @author elvea
 * @see OperationLogService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@Transactional
@RabbitListener(queues = OPERATION_LOG_QUEUE)
public class OperationLogAmqpServiceImpl extends AbstractAmqpService<OperationLogDto> implements OperationLogAmqpService {

    private final OperationLogService operationLogService;

    public OperationLogAmqpServiceImpl(OperationLogService operationLogService) {
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
