package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.system.BaseTests;

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
