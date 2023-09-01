package cn.elvea.platform.commons.core.cache;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.Duration;

import static cn.elvea.platform.commons.core.constants.GlobalConstants.DEFAULT_CACHE_DURATION;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface CacheKeyGenerator {

    @NonNull
    String getPrefix();

    @Nullable
    default Duration getExpire() {
        return DEFAULT_CACHE_DURATION;
    }

    default CacheKey key(Object... params) {
        String key = String.format("%s:%s", this.getPrefix(), StringUtils.arrayToDelimitedString(params, "_"));
        return new CacheKey(key, getExpire());
    }

    default CacheKey keyById(Long id) {
        return this.key("id", id);
    }

}
