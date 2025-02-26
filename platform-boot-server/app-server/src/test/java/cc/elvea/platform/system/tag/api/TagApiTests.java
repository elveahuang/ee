package cc.elvea.platform.system.tag.api;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemTagConstants;
import cc.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cc.elvea.platform.system.tag.model.request.TagTypeRequest;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
import cc.elvea.platform.system.tag.model.vo.TagVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class TagApiTests extends BaseTests {

    @Autowired
    TagApi tagApi;

    @Test
    public void baseTest() {
        TagTypeRequest request = TagTypeRequest.builder().type(SystemTagConstants.USER).build();
        TagTypeVo vo = this.tagApi.getTagType(request);
        Assertions.assertNotNull(vo);
    }

    @Test
    public void baseRelationTest() {
        TagTypeVo vo = this.tagApi.getTagType(TagTypeRequest.builder().type(SystemTagConstants.USER).build());
        Assertions.assertNotNull(vo);

        Long[] ids = vo.getItems().stream().map(TagVo::getId).toList().toArray(Long[]::new);
        TagRelationSaveRequest request = TagRelationSaveRequest.builder()
                .targetId(1L).targetType(SystemTagConstants.USER)
                .typeId(vo.getId()).ids(ids).build();
        this.tagApi.saveRelation(request);
    }

}
