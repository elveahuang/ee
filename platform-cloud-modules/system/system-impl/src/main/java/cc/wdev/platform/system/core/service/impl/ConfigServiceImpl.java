package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.cache.ConfigCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.entity.ConfigEntity;
import cc.wdev.platform.system.core.mapper.ConfigMapper;
import cc.wdev.platform.system.core.service.ConfigService;
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
