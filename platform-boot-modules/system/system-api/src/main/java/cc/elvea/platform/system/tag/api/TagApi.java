package cc.elvea.platform.system.tag.api;

import cc.elvea.platform.system.tag.model.form.TagForm;
import cc.elvea.platform.system.tag.model.request.TagRelationRequest;
import cc.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cc.elvea.platform.system.tag.model.request.TagSearchRequest;
import cc.elvea.platform.system.tag.model.request.TagTypeRequest;
import cc.elvea.platform.system.tag.model.vo.TagRelationVo;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
import cc.elvea.platform.system.tag.model.vo.TagVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface TagApi {

    /**
     * 获取标签类型
     */
    TagTypeVo getTagType(TagTypeRequest request);

    /**
     * 搜索标签
     */
    Page<TagVo> search(TagSearchRequest request);

    /**
     * 保存标签
     */
    void save(TagForm form);

    /**
     * 获取实体关联
     */
    TagRelationVo getRelation(TagRelationRequest request);

    /**
     * 保存实体关联
     */
    void saveRelation(TagRelationSaveRequest request);

}
