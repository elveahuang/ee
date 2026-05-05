package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.UserRoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
