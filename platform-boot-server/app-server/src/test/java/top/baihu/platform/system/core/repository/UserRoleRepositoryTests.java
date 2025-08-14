package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.UserRoleEntity;

import java.util.List;

/**
 * @author elvea
 */
public class UserRoleRepositoryTests extends BaseTests {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userRoleRepository);

        List<UserRoleEntity> list = this.userRoleRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
