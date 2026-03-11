package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
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
