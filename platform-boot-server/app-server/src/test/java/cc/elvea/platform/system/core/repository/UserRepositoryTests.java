package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class UserRepositoryTests extends BaseTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userRepository);

        List<UserEntity> list = this.userRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
