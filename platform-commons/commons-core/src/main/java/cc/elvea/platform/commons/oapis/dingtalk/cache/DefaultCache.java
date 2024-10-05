package cc.elvea.platform.commons.oapis.dingtalk.cache;

import cc.elvea.platform.commons.core.cache.service.CacheService;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @since 24.1.0
 */
public record DefaultCache(CacheService cacheService, String keyPrefix) implements Cache {

    @Override
    public String get(String key) {
        return this.cacheService.get(key);
    }

    @Override
    public void set(String key, String value, long expire, TimeUnit timeUnit) {
        this.cacheService.set(key, value, Duration.ofSeconds(expire));
    }

}
