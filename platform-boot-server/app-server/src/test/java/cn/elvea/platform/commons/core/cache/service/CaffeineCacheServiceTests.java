package cn.elvea.platform.commons.core.cache.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
public class CaffeineCacheServiceTests extends CacheServiceTests {

    @Autowired
    CaffeineCacheService caffeineCacheService;

    @Override
    protected CacheService cacheService() {
        return caffeineCacheService;
    }

    @Test
    public void base() throws InterruptedException {
        Assertions.assertNotNull(this.caffeineCacheService);

        String key = "test-cache", value = "1";
        this.caffeineCacheService.set(key, value);
        String id = this.caffeineCacheService.get(key);
        Assertions.assertNotNull(id);

        key = "test-cache-ttl";
        value = "1";
        this.caffeineCacheService.set(key, value, Duration.ofSeconds(5));
        id = this.caffeineCacheService.get(key);
        Assertions.assertNotNull(id);
        Thread.sleep(1000 * 5);
        id = this.caffeineCacheService.get(key);
        Assertions.assertNull(id);
    }

}
