package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;
import cn.elvea.platform.system.log.repository.OperationLogRepository;
import cn.elvea.platform.system.log.service.OperationLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see OperationLogService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class OperationLogServiceImpl
        extends BaseCachingEntityService<OperationLogEntity, Long, OperationLogRepository>
        implements OperationLogService {
}
