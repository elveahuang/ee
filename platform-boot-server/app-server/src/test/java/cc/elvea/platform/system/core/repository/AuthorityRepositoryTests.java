package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
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
