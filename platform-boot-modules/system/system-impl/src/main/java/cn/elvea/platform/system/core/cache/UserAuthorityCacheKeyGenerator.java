package cn.elvea.platform.system.core.cache;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 0.0.1
 */
public class UserAuthorityCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    @NotNull
    public String getPrefix() {
        return SystemCacheConstants.USER_AUTHORITY;
    }

    public static CacheKey keyByUserId(Long userId) {
        return new UserAuthorityCacheKeyGenerator().key(userId);
    }

}
