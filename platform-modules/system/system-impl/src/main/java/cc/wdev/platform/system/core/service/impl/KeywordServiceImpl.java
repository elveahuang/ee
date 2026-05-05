package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.KeywordEntity;
import cc.wdev.platform.system.core.repository.KeywordRepository;
import cc.wdev.platform.system.core.service.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.KEYWORD;

/**
 * @author elvea
 * @see KeywordService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
public class KeywordServiceImpl extends BaseCachingEntityService<KeywordEntity, Long, KeywordRepository> implements KeywordService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(KEYWORD).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
