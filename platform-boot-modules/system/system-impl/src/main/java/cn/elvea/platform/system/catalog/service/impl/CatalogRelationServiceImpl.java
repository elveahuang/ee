package cn.elvea.platform.system.catalog.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;
import cn.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import cn.elvea.platform.system.catalog.model.entity.CatalogRelationEntity_;
import cn.elvea.platform.system.catalog.repository.CatalogRelationRepository;
import cn.elvea.platform.system.catalog.service.CatalogRelationService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
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
 * @since 0.0.1
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
                        sb.append(GlobalConstants.RELATION_DELIMITER).append(r.getAncestorId());
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

    @Override
    public List<CatalogRelationEntity> getParents(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.entityId), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getDirectParents(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.parentInd), Boolean.TRUE));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.entityId), entityId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getChildren(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ancestorId), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public List<CatalogRelationEntity> getDirectChildren(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.parentInd), Boolean.TRUE));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ancestorId), ancestorId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.ancestorId), ancestorId));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsAncestor(String relationType, List<Long> ancestorIdList) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(CatalogRelationEntity_.ancestorId).in(ancestorIdList));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.entityId), entityId));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

    @Override
    public void deleteAsChild(String relationType, List<Long> entityIdList) {
        Specification<CatalogRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(root.get(CatalogRelationEntity_.entityId).in(entityIdList));
            predicates.add(builder.equal(root.get(CatalogRelationEntity_.relationType), relationType));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
