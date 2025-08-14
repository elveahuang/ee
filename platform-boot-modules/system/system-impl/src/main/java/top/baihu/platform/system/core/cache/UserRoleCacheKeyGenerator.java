package top.baihu.platform.system.core.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

/**
 * @author elvea
 */
public class UserRoleCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.USER_ROLE;
    }

    public CacheKey byUserId(Long userId) {
        return this.key(userId);
    }

    public static CacheKey keyByUserId(Long userId) {
        return new UserRoleCacheKeyGenerator().byUserId(userId);
    }

}
