package cc.wdev.platform.system.message.cache;

import cc.wdev.platform.commons.core.cache.CacheKey;
import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE;

/**
 * @author elvea
 */
public class MessageTemplateCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return MESSAGE_TEMPLATE;
    }

    public CacheKey byType(Long typeId, Long templateTypeId) {
        return this.key("code", typeId, templateTypeId);
    }

}
