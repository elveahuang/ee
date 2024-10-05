package cc.elvea.platform.commons.cache.service;

import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.core.cache.service.RedissonCacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author elvea
 * @since 24.1.0
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
