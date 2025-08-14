package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.OrganizationEntity;

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
