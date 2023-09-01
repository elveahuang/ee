package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.UserRoleEntity;
import cn.elvea.platform.system.core.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
