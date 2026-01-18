package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import cc.wdev.platform.system.core.domain.entity.OperatorEntity;
import cc.wdev.platform.system.core.repository.OperatorRepository;
import cc.wdev.platform.system.core.service.ConfigService;
import cc.wdev.platform.system.core.service.OperatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ConfigService
 * @see BaseCachingEntityService
 */
@Service
@AllArgsConstructor
public class OperatorServiceImpl extends BaseCachingEntityService<OperatorEntity, Long, OperatorRepository> implements OperatorService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ACTOR).build();

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
