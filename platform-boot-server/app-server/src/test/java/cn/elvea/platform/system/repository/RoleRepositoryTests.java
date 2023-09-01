package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import cn.elvea.platform.system.core.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
