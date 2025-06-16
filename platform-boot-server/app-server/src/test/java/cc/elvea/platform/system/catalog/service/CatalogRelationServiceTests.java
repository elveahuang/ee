package cc.elvea.platform.system.catalog.service;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
