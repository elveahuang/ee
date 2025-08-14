package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;

/**
 * @author elvea
 */
public class CatalogRelationServiceTests extends BaseTests {

    @Autowired
    CatalogRelationService catalogRelationService;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogRelationService);
    }

}
