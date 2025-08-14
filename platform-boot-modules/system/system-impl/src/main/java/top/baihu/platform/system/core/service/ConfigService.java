package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.ConfigEntity;

/**
 * @author elvea
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
