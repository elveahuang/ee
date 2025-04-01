package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.config.service.ConfigService;
import cc.elvea.platform.system.core.model.entity.OperatorEntity;
import cc.elvea.platform.system.core.repository.OperatorRepository;
import cc.elvea.platform.system.core.service.OperatorService;
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
