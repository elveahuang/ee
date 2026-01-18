package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.dto.EntityRelationSaveDto;
import cc.wdev.platform.system.core.domain.entity.EntityRelationEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface EntityRelationService extends CachingEntityService<EntityRelationEntity, Long> {

    /**
     * 保存实体关联
     */
    void saveEntityRelation(EntityRelationSaveDto saveDto);

    /**
     * 获取用户直属组织的ID
     */
    List<Long> getDirectOrganizationByUserId(Long userId);

    /**
     * 获取用户直属岗位的ID
     */
    List<Long> getDirectPositionByUserId(Long userId);

    /**
     * 获取所有上级
     *
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    List<EntityRelationEntity> getParents(String relationType, long entityId);

    /**
     * 获取所有直属上级
     *
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    List<EntityRelationEntity> getDirectParents(String relationType, long entityId);

    /**
     * 获取所有下级
     *
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    List<EntityRelationEntity> getChildren(String relationType, long ancestorId);

    /**
     * 获取所有直属下级
     *
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    List<EntityRelationEntity> getDirectChildren(String relationType, long ancestorId);

    /**
     * 删除下级关联
     */
    void deleteAsAncestor(String relationType, Long ancestorId);

    /**
     * 删除下级关联
     */
    void deleteAsAncestor(String relationType, List<Long> ancestorIdList);

    /**
     * 删除上级关联
     */
    void deleteAsChild(String relationType, Long entityId);

    /**
     * 删除上级关联
     */
    void deleteAsChild(String relationType, List<Long> entityIdList);

}
