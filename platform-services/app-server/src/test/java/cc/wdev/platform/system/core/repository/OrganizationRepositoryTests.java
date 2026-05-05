package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.OrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class OrganizationRepositoryTests extends BaseTests {

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.organizationRepository);

        List<OrganizationEntity> list = this.organizationRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
