package cc.wdev.platform.system.core.api;

import cc.wdev.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CatalogApiTests extends BaseTests {

    @Autowired
    CatalogApi catalogApi;

    @Test
    public void test() {
        Assertions.assertNotNull(catalogApi);
    }

}
