package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 */
public class RoleCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.ROLE;
    }

}
