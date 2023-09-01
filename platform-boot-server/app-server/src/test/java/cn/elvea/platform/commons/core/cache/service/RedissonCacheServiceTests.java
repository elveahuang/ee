package cn.elvea.platform.commons.core.cache.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author elvea
 * @since 0.0.1
 */
@ActiveProfiles("redisson")
public class RedissonCacheServiceTests extends CacheServiceTests {

    @Autowired
    CacheService cacheService;

    @Autowired
    RedissonCacheService redissonCacheService;

    @Override
    protected CacheService cacheService() {
        return this.cacheService;
    }

    @Test
    public void base() throws Exception {
        //
        super.base();
        //
        Assertions.assertNotNull(this.cacheService);
        Assertions.assertNotNull(this.redissonCacheService);
    }

}
