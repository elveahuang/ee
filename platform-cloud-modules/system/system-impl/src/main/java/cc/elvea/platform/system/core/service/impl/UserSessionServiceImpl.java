package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.core.mapper.UserSessionMapper;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;
import cc.elvea.platform.system.core.service.UserSessionService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UserSessionService
 * @see BaseCachingEntityService
 */
@Service
public class UserSessionServiceImpl extends BaseCachingEntityService<UserSessionEntity, Long, UserSessionMapper>
        implements UserSessionService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.USER_SESSION).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
