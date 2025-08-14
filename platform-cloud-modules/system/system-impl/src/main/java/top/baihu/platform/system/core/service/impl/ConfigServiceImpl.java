package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.cache.ConfigCacheKeyGenerator;
import top.baihu.platform.system.core.domain.entity.ConfigEntity;
import top.baihu.platform.system.core.mapper.ConfigMapper;
import top.baihu.platform.system.core.service.ConfigService;

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
