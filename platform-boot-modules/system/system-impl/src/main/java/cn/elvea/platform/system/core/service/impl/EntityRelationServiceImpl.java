package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity_;
import cn.elvea.platform.system.core.repository.EntityRelationRepository;
import cn.elvea.platform.system.core.service.EntityRelationService;
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
 * @since 0.0.1
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
                        sb.append(GlobalConstants.RELATION_DELIMITER).append(r.getAncestorId());
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

                sb.append(GlobalConstants.RELATION_DELIMITER).append(ancestorId).append(GlobalConstants.RELATION_DELIMITER);

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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.entityId), entityId));
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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.parentInd), Boolean.TRUE));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.entityId), entityId));
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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ancestorId), ancestorId));
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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.parentInd), Boolean.TRUE));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ancestorId), ancestorId));
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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.ancestorId), ancestorId));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
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
            predicates.add(root.get(EntityRelationEntity_.ancestorId).in(ancestorIdList));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
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
            predicates.add(builder.equal(root.get(EntityRelationEntity_.entityId), entityId));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
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
            predicates.add(root.get(EntityRelationEntity_.entityId).in(entityIdList));
            predicates.add(builder.equal(root.get(EntityRelationEntity_.relationType), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
