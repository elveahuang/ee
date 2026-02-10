package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.RoleAuthorityEntity;
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
