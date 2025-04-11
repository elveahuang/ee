package cc.elvea.platform.system.core.service;

import cc.elvea.platform.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
@Slf4j
public class RoleServiceTests extends BaseTests {

    @Autowired
    RoleService roleService;

    @Test
    public void base() {
        log.info("currentEntityClass - {}", this.roleService.currentEntityClass());
        log.info("currentEntityClassSimpleName - {}", this.roleService.currentEntityClass().getSimpleName().toLowerCase());
        log.info("currentEntityIdClass - {}", this.roleService.currentEntityIdClass());
        Assertions.assertNotNull(this.roleService);
        Assertions.assertNotNull(this.roleService.currentEntityClass());
        Assertions.assertNotNull(this.roleService.currentEntityIdClass());
        Assertions.assertNotNull(this.roleService.findCacheById(1L));
        Assertions.assertNotNull(this.roleService.findCacheByIds(Lists.newArrayList(1L)));
    }

}
