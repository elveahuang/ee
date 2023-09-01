package cn.elvea.platform.system.core.service;

import cn.elvea.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
public class RoleServiceTests extends BaseTests {

    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        Assertions.assertNotNull(roleService);
    }

}
