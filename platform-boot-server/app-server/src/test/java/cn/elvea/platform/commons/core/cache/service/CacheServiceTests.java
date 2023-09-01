package cn.elvea.platform.commons.core.cache.service;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class CacheServiceTests extends BaseTests {

    protected abstract CacheService cacheService();

    public void base() throws Exception {
        Assertions.assertNotNull(this.cacheService());
    }

}
