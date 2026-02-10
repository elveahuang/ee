package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.AuthorityEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
