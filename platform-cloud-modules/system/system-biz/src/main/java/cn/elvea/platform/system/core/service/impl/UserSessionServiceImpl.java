package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity;
import cn.elvea.platform.system.core.mapper.UserSessionMapper;
import cn.elvea.platform.system.core.service.UserSessionService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UserSessionService
 * @see BaseCachingEntityService
 * @since 0.0.1
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
