package cc.elvea.platform.system.core.service;

import cc.elvea.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class RoleServiceTests extends BaseTests {

    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        Assertions.assertNotNull(roleService);
    }

}
