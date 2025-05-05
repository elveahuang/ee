package cc.elvea.platform.system.message.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TYPE;

/**
 * @author elvea
 */
public class MessageTypeCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return MESSAGE_TYPE;
    }

    public CacheKey byCode(String code) {
        return this.key("code", code);
    }

}
