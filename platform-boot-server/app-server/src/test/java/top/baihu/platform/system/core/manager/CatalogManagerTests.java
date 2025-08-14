package top.baihu.platform.system.core.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;

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
