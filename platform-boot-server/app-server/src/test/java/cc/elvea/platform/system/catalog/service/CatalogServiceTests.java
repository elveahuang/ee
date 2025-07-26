package cc.elvea.platform.system.catalog.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.service.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CatalogServiceTests extends BaseTests {

    @Autowired
    CatalogService catalogService;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogService);
    }

}
