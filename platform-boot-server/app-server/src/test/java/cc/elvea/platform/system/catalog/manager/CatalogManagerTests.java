package cc.elvea.platform.system.catalog.manager;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.manager.CatalogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CatalogManagerTests extends BaseTests {

    @Autowired
    CatalogManager catalogManager;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogManager);
    }

}
