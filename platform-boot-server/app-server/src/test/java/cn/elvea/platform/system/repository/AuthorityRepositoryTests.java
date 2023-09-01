package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import cn.elvea.platform.system.core.repository.AuthorityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class AuthorityRepositoryTests extends BaseTests {

    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.authorityRepository);

        List<AuthorityEntity> list = this.authorityRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
