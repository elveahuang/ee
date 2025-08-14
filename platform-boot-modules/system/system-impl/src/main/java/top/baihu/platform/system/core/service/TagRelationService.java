package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagRelationEntity;
import top.baihu.platform.system.core.domain.request.TagRelationDeleteRequest;
import top.baihu.platform.system.core.domain.request.TagRelationRequest;
import top.baihu.platform.system.core.domain.request.TagRelationSaveRequest;

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
