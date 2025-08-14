package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.RoleEntity;

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
