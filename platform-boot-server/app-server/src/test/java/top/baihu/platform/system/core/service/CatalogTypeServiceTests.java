package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;

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
