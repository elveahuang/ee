package cc.elvea.platform.commons.core.cache;

import cc.elvea.platform.commons.constants.GlobalConstants;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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
        String key = String.format("%s:%s", this.getPrefix(), StringUtils.arrayToDelimitedString(params, "_"));
        return new CacheKey(key, getExpire());
    }

    default CacheKey keyById(Long id) {
        return this.key("id", id);
    }

    default CacheKey keyByCode(String code) {
        return this.key("key", code);
    }

}
