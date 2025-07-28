package cc.elvea.platform.system.catalog.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.core.service.CatalogTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CatalogTypeServiceTests extends BaseTests {

    @Autowired
    CatalogTypeService catalogTypeService;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogTypeService);
    }

}
