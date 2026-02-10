package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.commons.constants.SystemTagConstants;
import cc.wdev.platform.system.core.domain.entity.TagRelationEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author elvea
 */
public class TagRelationServiceTests extends BaseTests {

    @Autowired
    TagRelationService tagRelationService;

    @Test
    @Rollback(false)
    public void baseTest() {
        TagRelationEntity entity = TagRelationEntity.builder().build();
        entity.setTypeId(1L);
        entity.setItemId(1L);
        entity.setTargetId(1L);
        entity.setTargetType(SystemTagConstants.USER);
        this.tagRelationService.saveBatch(Lists.list(entity));
        Assertions.assertNotNull(entity.getId());
    }

}
