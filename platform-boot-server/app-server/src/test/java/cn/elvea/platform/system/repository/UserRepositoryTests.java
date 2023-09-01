package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
