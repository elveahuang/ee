package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.AuthorityEntity;

import java.util.List;

/**
 * @author elvea
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
