package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

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
