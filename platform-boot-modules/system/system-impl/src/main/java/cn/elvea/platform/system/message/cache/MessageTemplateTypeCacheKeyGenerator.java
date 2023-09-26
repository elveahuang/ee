package cn.elvea.platform.system.message.cache;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE_TYPE;

/**
 * @author elvea
 * @since 0.0.1
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
