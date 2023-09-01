package cn.elvea.platform.commons.core.extensions.captcha.store;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.service.CacheService;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
public class DefaultCaptchaStore implements CaptchaStore {

    private final CacheKeyGenerator cacheKeyGenerator;

    private final CacheService cacheService;

    public DefaultCaptchaStore(CacheService cacheService, String cacheKeyPrefix) {
        this.cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(cacheKeyPrefix).build();
        this.cacheService = cacheService;
    }

    @Override
    public String get(String key) {
        return this.cacheService.get(this.cacheKeyGenerator.key(key));
    }

    @Override
    public void set(String key, String value, Duration duration) {
        this.cacheService.set(this.cacheKeyGenerator.key(key), value, duration);
    }

}
