package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.domain.entity.DictRelationEntity;
import cc.elvea.platform.system.core.domain.request.DictRelationDeleteRequest;
import cc.elvea.platform.system.core.domain.request.DictRelationRequest;
import cc.elvea.platform.system.core.domain.request.DictRelationSaveRequest;

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
