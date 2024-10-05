package cc.elvea.platform.commons.cache.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import org.junit.jupiter.api.Assertions;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class CacheServiceTests extends BaseTests {

    protected abstract CacheService cacheService();

    public void base() throws Exception {
        Assertions.assertNotNull(this.cacheService());
    }

}
