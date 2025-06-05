package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.RoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class RoleRepositoryTests extends BaseTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.roleRepository);

        List<RoleEntity> list = this.roleRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
