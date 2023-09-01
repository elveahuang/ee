package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import cn.elvea.platform.system.core.repository.RoleAuthorityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class RoleAuthorityRepositoryTests extends BaseTests {

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.roleAuthorityRepository);

        List<RoleAuthorityEntity> list = this.roleAuthorityRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
