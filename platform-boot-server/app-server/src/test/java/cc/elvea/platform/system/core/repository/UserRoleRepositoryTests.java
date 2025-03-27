package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.UserRoleEntity;
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
