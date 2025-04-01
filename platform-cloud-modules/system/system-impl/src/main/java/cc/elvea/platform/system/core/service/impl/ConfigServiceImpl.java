package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.cache.ConfigCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.ConfigMapper;
import cc.elvea.platform.system.core.model.entity.ConfigEntity;
import cc.elvea.platform.system.core.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ConfigService
 */
@Slf4j
@Service
public class ConfigServiceImpl extends BaseCachingEntityService<ConfigEntity, Long, ConfigMapper> implements ConfigService {

    private final ConfigCacheKeyGenerator cacheKeyGenerator = new ConfigCacheKeyGenerator();

    @Override
    public ConfigCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

}
