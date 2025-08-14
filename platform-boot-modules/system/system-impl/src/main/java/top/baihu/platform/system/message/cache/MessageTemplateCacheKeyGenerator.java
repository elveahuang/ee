package top.baihu.platform.system.message.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;

import static top.baihu.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE;

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
