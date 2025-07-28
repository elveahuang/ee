package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.domain.entity.ConfigEntity;

/**
 * @author elvea
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
