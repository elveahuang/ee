package cc.elvea.platform.commons.cache.service;

import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.core.cache.service.RedisCacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author elvea
 * @since 24.1.0
 */
@ActiveProfiles("RedisTemplate")
public class RedisCacheServiceTests extends CacheServiceTests {

    @Autowired
    CacheService cacheService;

    @Autowired
    RedisCacheService redisCacheService;

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
        Assertions.assertNotNull(this.redisCacheService);
    }

}
