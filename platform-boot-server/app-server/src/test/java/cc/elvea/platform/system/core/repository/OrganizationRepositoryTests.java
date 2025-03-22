package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
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
