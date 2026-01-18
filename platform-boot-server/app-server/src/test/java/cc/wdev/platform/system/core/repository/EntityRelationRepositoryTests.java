package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.EntityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
