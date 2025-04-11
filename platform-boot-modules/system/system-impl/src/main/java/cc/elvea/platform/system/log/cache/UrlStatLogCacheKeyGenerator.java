package cc.elvea.platform.system.log.cache;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.URL_STAT;

/**
 * @author elvea
 */
public class UrlStatLogCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return URL_STAT;
    }

}
