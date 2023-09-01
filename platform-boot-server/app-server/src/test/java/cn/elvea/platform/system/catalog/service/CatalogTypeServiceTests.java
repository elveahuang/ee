package cn.elvea.platform.system.catalog.service;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
public class CatalogTypeServiceTests extends BaseTests {

    @Autowired
    CatalogTypeService catalogTypeService;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogTypeService);
    }

}
