package cc.elvea.platform.system.keyword.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.keyword.model.entity.KeywordEntity;
import cc.elvea.platform.system.keyword.repository.KeywordRepository;
import cc.elvea.platform.system.keyword.service.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.KEYWORD;

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
