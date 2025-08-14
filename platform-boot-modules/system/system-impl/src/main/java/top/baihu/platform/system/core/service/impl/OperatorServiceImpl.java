package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.commons.core.cache.SimpleCacheKeyGenerator;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;
import top.baihu.platform.system.core.domain.entity.OperatorEntity;
import top.baihu.platform.system.core.repository.OperatorRepository;
import top.baihu.platform.system.core.service.ConfigService;
import top.baihu.platform.system.core.service.OperatorService;

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
