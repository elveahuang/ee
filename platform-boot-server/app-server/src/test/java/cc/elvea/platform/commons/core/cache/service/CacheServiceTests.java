package cc.elvea.platform.commons.core.cache.service;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CacheServiceTests extends BaseTests {

    @Autowired
    CacheService cacheService;

    public void base() throws Exception {
        Assertions.assertNotNull(this.cacheService);
    }

}
