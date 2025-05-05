package cc.elvea.platform.system.tag.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemTagConstants;
import cc.elvea.platform.system.tag.model.entity.TagRelationEntity;
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
