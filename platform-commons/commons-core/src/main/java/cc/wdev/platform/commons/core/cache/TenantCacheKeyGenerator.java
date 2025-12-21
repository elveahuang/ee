package cc.wdev.platform.commons.core.cache;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import org.springframework.util.StringUtils;

public interface TenantCacheKeyGenerator extends CacheKeyGenerator {

    default CacheKey key(Object... params) {
        String key = String.format("%s:%s:%s",
                this.getPrefix(),
                TenantContext.getTenantId(),
                StringUtils.arrayToDelimitedString(params, "_"))
            .toLowerCase();
        return new CacheKey(key, getExpire());
    }

    default String cacheKey(Object... params) {
        return String.format("%s:%s:%s",
                this.getPrefix(),
                TenantContext.getTenantId(),
                StringUtils.arrayToDelimitedString(params, "_"))
            .toLowerCase();
    }
}
