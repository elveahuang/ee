package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.commons.enums.EntityRelationTypeEnum;
import cn.elvea.platform.system.core.model.converter.PositionConverter;
import cn.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cn.elvea.platform.system.core.model.dto.PositionDeleteDto;
import cn.elvea.platform.system.core.model.dto.PositionDto;
import cn.elvea.platform.system.core.model.dto.PositionSaveDto;
import cn.elvea.platform.system.core.model.entity.PositionEntity;
import cn.elvea.platform.system.core.model.entity.PositionEntity_;
import cn.elvea.platform.system.core.repository.PositionRepository;
import cn.elvea.platform.system.core.service.EntityRelationService;
import cn.elvea.platform.system.core.service.PositionService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @see PositionService
 * @see BaseCachingEntityService
 * @since 0.0.1
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
        PositionEntity entity = PositionConverter.INSTANCE.saveDto2Entity(saveDto);

        // 保存岗位基本信息
        this.save(entity);

        // 保存岗位关联信息
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
                .relationType(EntityRelationTypeEnum.ORG_PARENT_ORG.getValue())
                .ancestorIdList(Collections.singletonList(saveDto.getParentId()))
                .entityId(entity.getId())
                .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return PositionConverter.INSTANCE.entity2Dto(entity);
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
            predicates.add(builder.equal(root.get(PositionEntity_.rootInd), Boolean.TRUE));
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
            predicates.add(builder.equal(root.get(PositionEntity_.defaultInd), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
