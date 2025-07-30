package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemTagConstants;
import cc.elvea.platform.system.core.domain.request.TagRelationSaveRequest;
import cc.elvea.platform.system.core.domain.request.TagTypeRequest;
import cc.elvea.platform.system.core.domain.vo.TagTypeVo;
import cc.elvea.platform.system.core.domain.vo.TagVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class TagManagerTests extends BaseTests {

    @Autowired
    TagManager tagManager;

    @Test
    public void baseTest() {
        TagTypeRequest request = TagTypeRequest.builder().type(SystemTagConstants.USER).build();
        TagTypeVo vo = this.tagManager.getTagType(request);
        Assertions.assertNotNull(vo);
    }

    @Test
    public void baseRelationTest() {
        TagTypeVo vo = this.tagManager.getTagType(TagTypeRequest.builder().type(SystemTagConstants.USER).build());
        Assertions.assertNotNull(vo);

        Long[] ids = vo.getItems().stream().map(TagVo::getId).toList().toArray(Long[]::new);
        TagRelationSaveRequest request = TagRelationSaveRequest.builder()
            .targetId(1L).targetType(SystemTagConstants.USER)
            .typeId(vo.getId()).ids(ids).build();
        this.tagManager.saveRelation(request);
    }

}
