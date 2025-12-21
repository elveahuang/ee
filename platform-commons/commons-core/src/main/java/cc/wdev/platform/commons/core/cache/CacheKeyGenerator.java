package cc.wdev.platform.commons.core.cache;

import cc.wdev.platform.commons.constants.GlobalConstants;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @author elvea
 */
public interface CacheKeyGenerator {

    @NonNull
    String getPrefix();

    @Nullable
    default Duration getExpire() {
        return GlobalConstants.DEFAULT_CACHE_DURATION;
    }

    default CacheKey key(Object... params) {
        String key = String.format("%s:%s",
                this.getPrefix(),
                StringUtils.arrayToDelimitedString(params, "_"))
            .toLowerCase();
        return new CacheKey(key, getExpire());
    }

    default String cacheKey(Object... params) {
        return String.format("%s:%s",
                this.getPrefix(),
                StringUtils.arrayToDelimitedString(params, "_"))
            .toLowerCase();
    }

    default CacheKey keyById(Long id) {
        return this.key("id", id);
    }

    default CacheKey keyByCode(String code) {
        return this.key("code", code);
    }

    default CacheKey keyByGroupCode(String group, String code) {
        return this.key(group, code);
    }

    default CacheKey keyByUuid(String uuid) {
        return this.key("uuid", uuid);
    }

    default CacheKey keyByLong(Long uuid) {
        return this.key("key", uuid);
    }

}
