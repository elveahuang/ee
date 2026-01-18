package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.core.domain.entity.EntityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cc.wdev.platform.system.commons.enums.EntityRelationBizTypeEnum.USR_CURRENT_ORG;

/**
 * @author elvea
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
