package top.baihu.platform.system.core.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.cache.CacheKeyGenerator;
import top.baihu.platform.commons.core.cache.SimpleCacheKeyGenerator;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.commons.constants.SystemCacheConstants;
import top.baihu.platform.system.commons.enums.EntityRelationTypeEnum;
import top.baihu.platform.system.core.domain.converter.PositionConverter;
import top.baihu.platform.system.core.domain.dto.EntityRelationSaveDto;
import top.baihu.platform.system.core.domain.dto.PositionDeleteDto;
import top.baihu.platform.system.core.domain.dto.PositionDto;
import top.baihu.platform.system.core.domain.dto.PositionSaveDto;
import top.baihu.platform.system.core.domain.entity.PositionEntity;
import top.baihu.platform.system.core.domain.entity.PositionEntity_;
import top.baihu.platform.system.core.repository.PositionRepository;
import top.baihu.platform.system.core.service.EntityRelationService;
import top.baihu.platform.system.core.service.PositionService;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @see PositionService
 * @see BaseCachingEntityService
 */
@Service
@AllArgsConstructor
public class PositionServiceImpl extends BaseCachingEntityService<PositionEntity, Long, PositionRepository>
    implements PositionService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.POSITION).build();

    private final EntityRelationService entityRelationService;

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see PositionService#savePosition(PositionSaveDto)
     */
    @Override
    public PositionDto savePosition(PositionSaveDto saveDto) {
        PositionEntity entity = SpringUtils.getBean(PositionConverter.class).saveDto2Entity(saveDto);

        // 保存岗位基本信息
        this.save(entity);

        // 保存岗位关联信息
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
            .relationType(EntityRelationTypeEnum.ORG_PARENT_ORG.getValue())
            .ancestorIdList(Collections.singletonList(saveDto.getParentId()))
            .entityId(entity.getId())
            .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return SpringUtils.getBean(PositionConverter.class).entity2Dto(entity);
    }

    /**
     * @see PositionService#deletePosition(PositionDeleteDto)
     */
    @Override
    public void deletePosition(PositionDeleteDto deleteDto) {
    }

    /**
     * @see PositionService#getRootPosition()
     */
    @Override
    public PositionEntity getRootPosition() {
        Specification<PositionEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(PositionEntity_.ROOT_IND), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

    /**
     * @see PositionService#getDefaultPosition()
     */
    @Override
    public PositionEntity getDefaultPosition() {
        Specification<PositionEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(PositionEntity_.DEFAULT_IND), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
