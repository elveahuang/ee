package top.baihu.platform.system.core.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;

/**
 * @author elvea
 */
public class PositionCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.POSITION;
    }

}
