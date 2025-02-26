package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class RoleAuthorityRepositoryTests extends BaseTests {

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.roleAuthorityRepository);

        List<RoleAuthorityEntity> list = this.roleAuthorityRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
