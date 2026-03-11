package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.ConfigEntity;

/**
 * @author elvea
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
