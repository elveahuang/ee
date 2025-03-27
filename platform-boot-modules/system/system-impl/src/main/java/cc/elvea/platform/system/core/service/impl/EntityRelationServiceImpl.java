package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cc.elvea.platform.system.core.model.entity.EntityRelationEntity;
import cc.elvea.platform.system.core.model.entity.EntityRelationEntity_;
import cc.elvea.platform.system.core.repository.EntityRelationRepository;
import cc.elvea.platform.system.core.service.EntityRelationService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author elvea
 * @see EntityRelationService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
public class EntityRelationServiceImpl extends BaseCachingEntityService<EntityRelationEntity, Long, EntityRelationRepository>
        implements EntityRelationService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ENTITY_RELATION).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see EntityRelationService#saveEntityRelation(EntityRelationSaveDto)
     */
    @Override
    public void saveEntityRelation(EntityRelationSaveDto saveDto) {
        String ancestorRelationType = StringUtils.isNotEmpty(saveDto.getAncestorRelationType()) ?
                saveDto.getAncestorRelationType() : saveDto.getRelationType();

        List<EntityRelationEntity> relationList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(saveDto.getAncestorIdList())) {
            for (Long ancestorId : saveDto.getAncestorIdList()) {
                List<EntityRelationEntity> entityRelationList = com.google.common.collect.Lists.newArrayList();

                AtomicInteger index = new AtomicInteger(1);

                List<EntityRelationEntity> ancestorRelationList = this.getParents(ancestorRelationType, ancestorId);

                StringBuilder sb = new StringBuilder();

                // 增加直接上级的上级关联
                if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
                    entityRelationList.addAll(ancestorRelationList.stream().map(r -> {
                        // 关联路径
                        sb.append(GlobalConstants.DELIMITER).append(r.getAncestorId());
                        // 构建关联
                        return EntityRelationEntity.builder()
                                .ancestorId(r.getAncestorId())
                                .entityId(saveDto.getEntityId())
                                .parentInd(Boolean.FALSE)
                                .relationType(saveDto.getRelationType())
                                .relationIndex(index.getAndIncrement())
                                .build();
                    }).toList());
                }

                // 增加直接上级的关联
                entityRelationList.add(EntityRelationEntity.builder()
                        .ancestorId(ancestorId)
                        .entityId(saveDto.getEntityId())
                        .parentInd(Boolean.TRUE)
                        .relationType(saveDto.getRelationType())
                        .relationIndex(index.getAndIncrement())
                        .build()
                );

                sb.append(GlobalConstants.DELIMITER).append(ancestorId).append(GlobalConstants.DELIMITER);

                // 处理完整关联路径
                entityRelationList.forEach(r -> r.setRelationPath(sb.toString()));

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
     * @see EntityRelationService#getParents(String, Long)
     */
    @Override
    public List<EntityRelationEntity> getParents(String relationType, Long entityId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_PATH), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ENTITY_ID), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see EntityRelationService#getDirectParents(String, Long)
     */
    @Override
    public List<EntityRelationEntity> getDirectParents(String relationType, Long entityId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.PARENT_IND), Boolean.TRUE));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ENTITY_ID), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see EntityRelationService#getChildren(String, Long)
     */
    @Override
    public List<EntityRelationEntity> getChildren(String relationType, Long ancestorId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ANCESTOR_ID), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see EntityRelationService#getDirectChildren(String, Long)
     */
    @Override
    public List<EntityRelationEntity> getDirectChildren(String relationType, Long ancestorId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.PARENT_IND), Boolean.TRUE));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ANCESTOR_ID), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see EntityRelationService#deleteAsAncestor(String, Long)
     */
    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ANCESTOR_ID), ancestorId));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    /**
     * @see EntityRelationService#deleteAsAncestor(String, List)
     */
    @Override
    public void deleteAsAncestor(String relationType, List<Long> ancestorIdList) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(EntityRelationEntity_.ANCESTOR_ID).in(ancestorIdList));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    /**
     * @see EntityRelationService#deleteAsChild(String, Long)
     */
    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ENTITY_ID), entityId));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    /**
     * @see EntityRelationService#deleteAsChild(String, List)
     */
    @Override
    public void deleteAsChild(String relationType, List<Long> entityIdList) {
        Specification<EntityRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(EntityRelationEntity_.ENTITY_ID).in(entityIdList));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
