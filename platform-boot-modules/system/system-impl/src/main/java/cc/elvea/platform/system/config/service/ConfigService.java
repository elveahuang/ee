package cc.elvea.platform.system.config.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.config.model.entity.ConfigEntity;

/**
 * @author elvea
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
