package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.cache.ConfigCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.ConfigEntity;
import cn.elvea.platform.system.core.mapper.ConfigMapper;
import cn.elvea.platform.system.core.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ConfigService
 * @since 0.0.1
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
