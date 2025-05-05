package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.data.jpa.service.BaseEntityService;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import cc.elvea.platform.system.log.repository.OperationLogRepository;
import cc.elvea.platform.system.log.service.OperationLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author elvea
 * @see OperationLogService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OperationLogServiceImpl extends BaseEntityService<OperationLogEntity, Long, OperationLogRepository> implements OperationLogService {
}
