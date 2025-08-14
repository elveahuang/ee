package top.baihu.platform.system.message.cache;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;

import static top.baihu.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE_TYPE;

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
