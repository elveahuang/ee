package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.commons.core.cache.SimpleCacheKeyGenerator;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;
import top.baihu.platform.system.core.domain.entity.UserSessionEntity;
import top.baihu.platform.system.core.mapper.UserSessionMapper;
import top.baihu.platform.system.core.service.UserSessionService;

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
