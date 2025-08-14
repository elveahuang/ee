package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.message.rabbit.AbstractAmqpService;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.OperationLogConverter;
import top.baihu.platform.system.core.domain.entity.OperationLogEntity;
import top.baihu.platform.system.core.service.OperationLogAmqpService;
import top.baihu.platform.system.core.service.OperationLogService;

import static top.baihu.platform.system.commons.constants.SystemAmqpConstants.OPERATION_LOG_QUEUE;

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
