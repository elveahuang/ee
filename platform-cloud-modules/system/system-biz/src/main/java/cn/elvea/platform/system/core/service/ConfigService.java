package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.entity.ConfigEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 0.0.1
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {
}
