package cc.elvea.platform.system.catalog.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.catalog.mapper.CatalogRelationMapper;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import cc.elvea.platform.system.catalog.service.CatalogRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.*;
import static cc.elvea.platform.system.commons.constants.SystemConstants.CATALOG_RELATION_CATALOG_PARENT_CATALOG;
import static cc.elvea.platform.system.commons.constants.SystemConstants.CATALOG_RELATION_DELIMITER;

/**
 * @author elvea
 * @see CatalogRelationService
 */
@Service
@Slf4j
public class CatalogRelationServiceImpl extends BaseEntityService<CatalogRelationEntity, Long, CatalogRelationMapper> implements CatalogRelationService {

    /**
     * @see CatalogRelationService#getParents(String, long)
     */
    @Override
    @Cacheable(value = CACHE_PARENT_CATALOG_RELATION, key = "#relationType + '-' + #entityId")
    public List<CatalogRelationEntity> getParents(String relationType, long entityId) {
        return this.getMapper().getParents(relationType, entityId);
    }

    /**
     * @see CatalogRelationService#getDirectParents(String, long)
     */
    @Override
    @Cacheable(value = CACHE_DIRECT_PARENT_CATALOG_RELATION, key = "#relationType + '-' + #entityId")
    public List<CatalogRelationEntity> getDirectParents(String relationType, long entityId) {
        return this.getMapper().getDirectParents(relationType, entityId);
    }

    /**
     * @see CatalogRelationService#getChildren(String, long)
     */
    @Override
    @Cacheable(value = CACHE_CHILD_CATALOG_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<CatalogRelationEntity> getChildren(String relationType, long ancestorId) {
        return this.getMapper().getChildren(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationService#getDirectChildren(String, long)
     */
    @Override
    @Cacheable(value = CACHE_DIRECT_CHILD_CATALOG_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<CatalogRelationEntity> getDirectChildren(String relationType, long ancestorId) {
        return this.getMapper().getDirectChildren(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationService#updateEntityRelations(String, Long, Long)
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_PARENT_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_DIRECT_PARENT_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_CHILD_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_DIRECT_CHILD_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true)
    })
    public void updateEntityRelations(String relationType, Long entityId, Long ancestorId) {

        List<CatalogRelationEntity> relationList = new ArrayList<>();

        // 根据关联类型，获取上级的关联类型，以便查询上级所有已有关联
        String ancestorRelationType = null;
        if (CATALOG_RELATION_CATALOG_PARENT_CATALOG.equalsIgnoreCase(relationType)) {
            ancestorRelationType = CATALOG_RELATION_CATALOG_PARENT_CATALOG;
        } else {
            //
        }

        List<CatalogRelationEntity> ancestorRelationList = this.getParents(ancestorRelationType, ancestorId);

        AtomicInteger index = new AtomicInteger(1);

        StringBuilder pathBuilder = new StringBuilder();

        // 先增加上级的上级关联
        if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
            relationList = ancestorRelationList.stream().map(r -> {

                // 关联路径
                pathBuilder.append(CATALOG_RELATION_DELIMITER).append(r.getAncestorId());

                // 构建关联
                return CatalogRelationEntity.builder()
                        .ancestorId(r.getAncestorId())
                        .entityId(entityId)
                        .parentInd(Boolean.FALSE)
                        .relationType(relationType)
                        .relationIndex(index.getAndIncrement())
                        .build();
            }).collect(Collectors.toList());
        }

        // 增加上级关联
        relationList.add(CatalogRelationEntity.builder()
                .ancestorId(ancestorId)
                .entityId(entityId)
                .parentInd(Boolean.TRUE)
                .relationType(relationType)
                .relationIndex(index.getAndIncrement())
                .build()
        );
        pathBuilder.append(CATALOG_RELATION_DELIMITER).append(ancestorId).append(CATALOG_RELATION_DELIMITER);

        // 处理完整关联路径
        relationList.forEach(r -> {
            r.setFullPath(pathBuilder.toString());
        });

        // 删除已有的关联记录，然后保存新的关联记录

        this.getMapper().deleteAsChild(relationType, entityId);

        if (CollectionUtils.isNotEmpty(relationList)) {
            this.saveBatch(relationList);
        }

    }

    /**
     * @see CatalogRelationService#deleteAsAncestor(String, Long)
     */
    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        this.getMapper().deleteAsAncestor(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationService#deleteAsChild(String, Long)
     */
    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        this.getMapper().deleteAsChild(relationType, entityId);
    }

    /**
     * @see CatalogRelationService#clearCache()
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_PARENT_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_DIRECT_PARENT_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_CHILD_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_DIRECT_CHILD_CATALOG_RELATION, beforeInvocation = true)
    })
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.info("Clear cache [{}]...", CACHE_PARENT_CATALOG_RELATION);
            log.info("Clear cache [{}]...", CACHE_DIRECT_PARENT_CATALOG_RELATION);
            log.info("Clear cache [{}]...", CACHE_CHILD_CATALOG_RELATION);
            log.info("Clear cache [{}]...", CACHE_DIRECT_CHILD_CATALOG_RELATION);
        }
    }

}
