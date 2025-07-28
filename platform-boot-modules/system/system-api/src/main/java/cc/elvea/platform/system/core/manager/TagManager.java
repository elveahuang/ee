package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.system.core.model.form.TagForm;
import cc.elvea.platform.system.core.model.request.TagRelationRequest;
import cc.elvea.platform.system.core.model.request.TagRelationSaveRequest;
import cc.elvea.platform.system.core.model.request.TagSearchRequest;
import cc.elvea.platform.system.core.model.request.TagTypeRequest;
import cc.elvea.platform.system.core.model.vo.TagRelationVo;
import cc.elvea.platform.system.core.model.vo.TagTypeVo;
import cc.elvea.platform.system.core.model.vo.TagVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface TagManager {

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
