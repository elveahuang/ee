package cc.wdev.platform.system.core.cache;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.URL_STAT;

/**
 * @author elvea
 */
public class UrlStatLogCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return URL_STAT;
    }

}
