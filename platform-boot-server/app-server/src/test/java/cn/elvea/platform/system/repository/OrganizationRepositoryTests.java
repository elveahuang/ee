package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;
import cn.elvea.platform.system.core.repository.OrganizationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
