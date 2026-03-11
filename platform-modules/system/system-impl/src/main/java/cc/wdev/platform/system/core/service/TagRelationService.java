package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TagRelationEntity;
import cc.wdev.platform.system.core.domain.request.TagRelationDeleteRequest;
import cc.wdev.platform.system.core.domain.request.TagRelationRequest;
import cc.wdev.platform.system.core.domain.request.TagRelationSaveRequest;

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
