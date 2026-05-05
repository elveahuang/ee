package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.OperationLogEntity;
import cc.wdev.platform.system.core.repository.OperationLogRepository;
import cc.wdev.platform.system.core.service.OperationLogService;
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
