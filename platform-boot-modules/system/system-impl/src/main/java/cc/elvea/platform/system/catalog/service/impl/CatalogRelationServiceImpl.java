package cc.elvea.platform.system.catalog.service.impl;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity_;
import cc.elvea.platform.system.catalog.repository.CatalogRelationRepository;
import cc.elvea.platform.system.catalog.service.CatalogRelationService;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
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
 */
@Slf4j
@Service
public class CatalogRelationServiceImpl extends BaseCachingEntityService<CatalogRelationEntity, Long, CatalogRelationRepository> implements CatalogRelationService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.CATALOG_RELATION).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see EntityRelationService#saveEntityRelation(EntityRelationSaveDto)
     */
    @Override
    public void saveCatalogRelation(CatalogRelationSaveDto saveDto) {
        List<CatalogRelationEntity> relationList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(saveDto.getAncestorIdList())) {
            for (Long ancestorId : saveDto.getAncestorIdList()) {
                AtomicInteger index = new AtomicInteger(1);
                List<CatalogRelationEntity> entityRelationList = Lists.newArrayList();

                StringBuilder sb = new StringBuilder();
                List<CatalogRelationEntity> ancestorRelationList = this.getParents(saveDto.getAncestorRelationType(), ancestorId);
                // 增加直接上级的上级关联
                if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
                    entityRelationList.addAll(ancestorRelationList.stream().map(r -> {
                        // 关联路径
                        sb.append(GlobalConstants.DELIMITER).append(r.getAncestorId());
                        // 构建关联
                        return CatalogRelationEntity.builder()
                                .ancestorId(r.getAncestorId())
                                .entityId(saveDto.getEntityId())
                                .parentInd(Boolean.FALSE)
                                .relationType(saveDto.getRelationType())
                                .relationIndex(index.getAndIncrement())
                                .build();
                    }).toList());
                }

                // 增加直接上级的关联
                entityRelationList.add(CatalogRelationEntity.builder()
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

    @Override
    public List<CatalogRelationEntity> getParents(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ENTITY_ID), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getDirectParents(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.PARENT_IND), Boolean.TRUE));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ENTITY_ID), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getChildren(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ANCESTOR_ID), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getDirectChildren(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.PARENT_IND), Boolean.TRUE));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ANCESTOR_ID), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ANCESTOR_ID), ancestorId));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsAncestor(String relationType, List<Long> ancestorIdList) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(CatalogRelationEntity_.ANCESTOR_ID).in(ancestorIdList));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ENTITY_ID), entityId));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsChild(String relationType, List<Long> entityIdList) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(CatalogRelationEntity_.ENTITY_ID).in(entityIdList));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.RELATION_TYPE), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
