package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.RoleAuthorityEntity;

import java.util.List;

/**
 * @author elvea
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
