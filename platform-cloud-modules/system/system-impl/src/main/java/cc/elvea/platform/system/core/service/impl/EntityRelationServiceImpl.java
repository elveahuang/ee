package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.commons.constants.SystemConstants;
import cc.elvea.platform.system.core.mapper.EntityRelationMapper;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cc.elvea.platform.system.core.model.entity.EntityRelationEntity;
import cc.elvea.platform.system.core.service.EntityRelationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see EntityRelationService
 */
@Slf4j
@Service
public class EntityRelationServiceImpl extends BaseCachingEntityService<EntityRelationEntity, Long, EntityRelationMapper> implements EntityRelationService {

    /**
     * @see EntityRelationService#saveEntityRelation(EntityRelationSaveDto)
     */
    @Override
    public void saveEntityRelation(EntityRelationSaveDto saveDto) {
        // 根据关联类型，获取上级的关联类型，以便查询上级所有已有关联
        String ancestorRelationType;
        if (SystemConstants.ERN_ORG_PARENT_ORG.equalsIgnoreCase(saveDto.getRelationType())) {
            ancestorRelationType = SystemConstants.ERN_ORG_PARENT_ORG;
        } else if (SystemConstants.ERN_PST_PARENT_PST.equalsIgnoreCase(saveDto.getRelationType())) {
            ancestorRelationType = SystemConstants.ERN_PST_PARENT_PST;
        } else if (SystemConstants.ERN_USR_CURRENT_ORG.equalsIgnoreCase(saveDto.getRelationType())) {
            ancestorRelationType = SystemConstants.ERN_ORG_PARENT_ORG;
        } else if (SystemConstants.ERN_USR_CURRENT_PST.equalsIgnoreCase(saveDto.getRelationType())) {
            ancestorRelationType = SystemConstants.ERN_PST_PARENT_PST;
        } else {
            log.error("Invalid relation type [{}]. entityId [{}].", saveDto.getRelationType(), saveDto.getEntityId());
            return;
        }

        List<EntityRelationEntity> relationList = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(saveDto.getAncestorIdList())) {
            for (Long ancestorId : saveDto.getAncestorIdList()) {
                List<EntityRelationEntity> ancestorRelationList = this.getParents(ancestorRelationType, ancestorId);

                AtomicInteger index = new AtomicInteger(1);
                StringBuilder pathBuilder = new StringBuilder();

                // 增加上级的上级关联
                List<EntityRelationEntity> entityRelationList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
                    entityRelationList.addAll(ancestorRelationList.stream().map(r -> {
                        // 关联路径
                        pathBuilder.append(SystemConstants.ENTITY_RELATION_DELIMITER).append(r.getAncestorId());
                        // 构建关联
                        return EntityRelationEntity.builder()
                                .ancestorId(r.getAncestorId())
                                .entityId(saveDto.getEntityId())
                                .parentInd(Boolean.FALSE)
                                .relationType(saveDto.getRelationType())
                                .index_(index.getAndIncrement())
                                .build();
                    }).toList());
                }

                // 增加直接的上级关联
                entityRelationList.add(EntityRelationEntity.builder()
                        .ancestorId(ancestorId)
                        .entityId(saveDto.getEntityId())
                        .parentInd(Boolean.TRUE)
                        .relationType(saveDto.getRelationType())
                        .index_(index.getAndIncrement())
                        .build()
                );

                pathBuilder.append(SystemConstants.ENTITY_RELATION_DELIMITER).append(ancestorId).append(SystemConstants.ENTITY_RELATION_DELIMITER);

                // 处理完整关联路径
                entityRelationList.forEach(r -> r.setPath_(pathBuilder.toString()));

                relationList.addAll(entityRelationList);
            }
        }
        // 删除已有的关联记录
        this.deleteAsChild(saveDto.getRelationType(), saveDto.getEntityId());
        // 保存新的关联记录
        if (CollectionUtils.isNotEmpty(relationList)) {
            this.saveBatch(relationList);
        }
    }

    /**
     * @see EntityRelationService#getDirectOrganizationByUserId(Long)
     */
    @Override
    public List<Long> getDirectOrganizationByUserId(Long userId) {
        List<EntityRelationEntity> relationEntityList = this.getDirectParents(SystemConstants.ERN_USR_CURRENT_ORG, userId);
        if (CollectionUtils.isNotEmpty(relationEntityList)) {
            return relationEntityList.stream().map(EntityRelationEntity::getAncestorId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * @see EntityRelationService#getDirectPositionByUserId(Long)
     */
    @Override
    public List<Long> getDirectPositionByUserId(Long userId) {
        List<EntityRelationEntity> relationEntityList = this.getDirectParents(SystemConstants.ERN_USR_CURRENT_PST, userId);
        if (CollectionUtils.isNotEmpty(relationEntityList)) {
            return relationEntityList.stream().map(EntityRelationEntity::getAncestorId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * @see EntityRelationService#getParents(String, long)
     */
    @Override
    public List<EntityRelationEntity> getParents(String relationType, long entityId) {
        return lambdaQueryWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getEntityId, entityId)
                .orderByAsc(EntityRelationEntity::getIndex_)
                .list();
    }

    /**
     * @see EntityRelationService#getDirectParents(String, long)
     */
    @Override
    public List<EntityRelationEntity> getDirectParents(String relationType, long entityId) {
        return lambdaQueryWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getEntityId, entityId)
                .eq(EntityRelationEntity::getParentInd, Boolean.TRUE)
                .list();
    }

    /**
     * @see EntityRelationService#getChildren(String, long)
     */
    @Override
    public List<EntityRelationEntity> getChildren(String relationType, long ancestorId) {
        return lambdaQueryWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getAncestorId, ancestorId)
                .list();
    }

    /**
     * @see EntityRelationService#getDirectChildren(String, long)
     */
    @Override
    public List<EntityRelationEntity> getDirectChildren(String relationType, long ancestorId) {
        return lambdaQueryWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getAncestorId, ancestorId)
                .eq(EntityRelationEntity::getParentInd, Boolean.TRUE)
                .list();
    }

    /**
     * @see EntityRelationService#deleteAsAncestor(String, Long)
     */
    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        lambdaUpdateWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getAncestorId, ancestorId)
                .remove();
    }

    /**
     * @see EntityRelationService#deleteAsAncestor(String, List)
     */
    @Override
    public void deleteAsAncestor(String relationType, List<Long> ancestorIdList) {
        if (CollectionUtils.isNotEmpty(ancestorIdList)) {
            lambdaUpdateWrapper()
                    .eq(EntityRelationEntity::getRelationType, relationType)
                    .in(EntityRelationEntity::getAncestorId, ancestorIdList)
                    .remove();
        }
    }

    /**
     * @see EntityRelationService#deleteAsChild(String, Long)
     */
    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        lambdaUpdateWrapper()
                .eq(EntityRelationEntity::getRelationType, relationType)
                .eq(EntityRelationEntity::getEntityId, entityId)
                .remove();
    }

    /**
     * @see EntityRelationService#deleteAsChild(String, List)
     */
    @Override
    public void deleteAsChild(String relationType, List<Long> entityIdList) {
        if (CollectionUtils.isNotEmpty(entityIdList)) {
            lambdaUpdateWrapper()
                    .eq(EntityRelationEntity::getRelationType, relationType)
                    .in(EntityRelationEntity::getEntityId, entityIdList)
                    .remove();
        }
    }

}
