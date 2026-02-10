package cc.wdev.platform.system.message.cache;

import cc.wdev.platform.commons.core.cache.CacheKey;
import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE_TYPE;

/**
 * @author elvea
 */
public class MessageTemplateTypeCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return MESSAGE_TEMPLATE_TYPE;
    }

    public CacheKey byCode(String code) {
        return this.key("code", code);
    }

}
