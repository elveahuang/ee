package cn.elvea.platform.system.core.service;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.elvea.platform.system.commons.enums.EntityRelationTypeEnum.USR_CURRENT_ORG;

/**
 * @author elvea
 * @since 0.0.1
 */
public class EntityRelationServiceTests extends BaseTests {

    @Autowired
    EntityRelationService entityRelationService;

    @Test
    public void baseTest() {
        List<EntityRelationEntity> parentList = entityRelationService.getParents(USR_CURRENT_ORG.getValue(), 1L);
        List<EntityRelationEntity> directParentList = entityRelationService.getDirectParents(USR_CURRENT_ORG.getValue(), 1L);
        Assertions.assertNotNull(parentList);
        Assertions.assertNotNull(directParentList);
    }

}
