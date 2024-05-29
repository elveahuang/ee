package cc.elvea.platform.commons.oapis.baidu.cache;

import cc.elvea.platform.commons.cache.service.CacheService;

import java.time.Duration;

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
    public void set(String key, String value, Duration duration) {
        this.cacheService.set(key, value, duration);
    }

}
