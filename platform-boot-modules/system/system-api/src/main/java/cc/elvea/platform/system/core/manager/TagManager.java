package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.system.core.domain.form.TagForm;
import cc.elvea.platform.system.core.domain.request.TagRelationRequest;
import cc.elvea.platform.system.core.domain.request.TagRelationSaveRequest;
import cc.elvea.platform.system.core.domain.request.TagSearchRequest;
import cc.elvea.platform.system.core.domain.request.TagTypeRequest;
import cc.elvea.platform.system.core.domain.vo.TagRelationVo;
import cc.elvea.platform.system.core.domain.vo.TagTypeVo;
import cc.elvea.platform.system.core.domain.vo.TagVo;
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
