package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.entity.TagRelationEntity;
import cc.elvea.platform.system.core.model.request.TagRelationDeleteRequest;
import cc.elvea.platform.system.core.model.request.TagRelationRequest;
import cc.elvea.platform.system.core.model.request.TagRelationSaveRequest;

import java.util.List;

/**
 * @author elvea
 */
public interface TagRelationService extends CachingEntityService<TagRelationEntity, Long> {

    /**
     * 查询目标实体关联
     */
    List<TagRelationEntity> findRelations(TagRelationRequest request);

    /**
     * 保存目标实体关联
     */
    void saveRelation(TagRelationSaveRequest request);

    /**
     * 删除目标实体关联
     */
    void deleteRelation(TagRelationRequest request);

    /**
     * 删除关联信息
     */
    void deleteRelation(TagRelationDeleteRequest request);

}
