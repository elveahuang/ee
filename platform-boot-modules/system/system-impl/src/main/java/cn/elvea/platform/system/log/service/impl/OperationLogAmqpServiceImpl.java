package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.log.dto.OperationLogDto;
import cn.elvea.platform.commons.core.message.amqp.AbstractAmqpService;
import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cn.elvea.platform.system.log.model.converter.OperationLogConverter;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;
import cn.elvea.platform.system.log.service.OperationLogAmqpService;
import cn.elvea.platform.system.log.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see OperationLogService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.OPERATION_LOG_QUEUE)
public class OperationLogAmqpServiceImpl extends AbstractAmqpService<OperationLogDto> implements OperationLogAmqpService {

    private final OperationLogService operationLogService;

    public OperationLogAmqpServiceImpl(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Override
    public void execute(OperationLogDto dto) {
        OperationLogEntity entity = OperationLogConverter.INSTANCE.dto2Entity(dto);
        this.operationLogService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.OPERATION_LOG_QUEUE;
    }

}
