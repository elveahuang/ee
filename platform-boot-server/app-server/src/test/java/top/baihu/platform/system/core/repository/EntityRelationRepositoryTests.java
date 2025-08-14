package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.EntityRelationEntity;

import java.util.List;

/**
 * @author elvea
 */
public class EntityRelationRepositoryTests extends BaseTests {

    @Autowired
    EntityRelationRepository entityRelationRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationRepository);

        List<EntityRelationEntity> list = this.entityRelationRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
