package top.baihu.platform.system.core.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;

import static top.baihu.platform.system.commons.constants.SystemCacheConstants.URL_STAT;

/**
 * @author elvea
 */
public class UrlStatLogCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return URL_STAT;
    }

}
