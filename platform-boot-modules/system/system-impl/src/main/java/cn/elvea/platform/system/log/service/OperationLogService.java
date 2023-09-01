package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface OperationLogService extends CachingEntityService<OperationLogEntity, Long> {
}
