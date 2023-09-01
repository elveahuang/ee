package cn.elvea.platform.system.core.cache;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * AuthorityCacheKeyGenerator
 *
 * @author elvea
 * @since 0.0.1
 */
public class AuthorityCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.AUTHORITY;
    }

}
