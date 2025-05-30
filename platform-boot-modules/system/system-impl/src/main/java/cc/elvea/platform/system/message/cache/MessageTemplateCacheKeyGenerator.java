package cc.elvea.platform.system.message.cache;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE;

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
