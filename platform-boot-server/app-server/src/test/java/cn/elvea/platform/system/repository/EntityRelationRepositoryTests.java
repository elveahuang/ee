package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import cn.elvea.platform.system.core.repository.EntityRelationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
