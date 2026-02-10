package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictRelationEntity;
import cc.wdev.platform.system.core.domain.request.DictRelationDeleteRequest;
import cc.wdev.platform.system.core.domain.request.DictRelationRequest;
import cc.wdev.platform.system.core.domain.request.DictRelationSaveRequest;

import java.util.List;

/**
 * @author elvea
 */
public interface DictRelationService extends CachingEntityService<DictRelationEntity, Long> {

    /**
     * 查询目标实体关联信息
     */
    List<DictRelationEntity> findRelations(DictRelationRequest request);

    /**
     * 保存目标实体关联信息
     */
    void saveRelation(DictRelationSaveRequest request);

    /**
     * 删除目标实体关联
     */
    void deleteRelation(DictRelationRequest request);

    /**
     * 删除关联信息
     */
    void deleteRelation(DictRelationDeleteRequest request);

}
