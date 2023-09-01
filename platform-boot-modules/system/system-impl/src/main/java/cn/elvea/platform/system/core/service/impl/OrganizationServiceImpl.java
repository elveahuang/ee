package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.commons.enums.EntityRelationTypeEnum;
import cn.elvea.platform.system.core.model.converter.OrganizationConverter;
import cn.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cn.elvea.platform.system.core.model.dto.OrganizationDeleteDto;
import cn.elvea.platform.system.core.model.dto.OrganizationDto;
import cn.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity_;
import cn.elvea.platform.system.core.repository.OrganizationRepository;
import cn.elvea.platform.system.core.service.EntityRelationService;
import cn.elvea.platform.system.core.service.OrganizationService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @see OrganizationService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl extends BaseCachingEntityService<OrganizationEntity, Long, OrganizationRepository>
        implements OrganizationService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ORGANIZATION).build();

    private final EntityRelationService entityRelationService;

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see OrganizationService#saveOrganization(OrganizationSaveDto)
     */
    @Override
    public OrganizationDto saveOrganization(OrganizationSaveDto saveDto) {
        OrganizationEntity entity = OrganizationConverter.INSTANCE.saveDto2Entity(saveDto);

        // 保存部门基本信息
        this.save(entity);

        // 保存部门关联信息
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
                .relationType(EntityRelationTypeEnum.ORG_PARENT_ORG.getValue())
                .ancestorIdList(Collections.singletonList(saveDto.getParentId()))
                .entityId(entity.getId())
                .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return OrganizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see OrganizationService#deleteOrganization(OrganizationDeleteDto)
     */
    @Override
    public void deleteOrganization(OrganizationDeleteDto deleteDto) {
    }

    /**
     * @see OrganizationService#getRootOrganization()
     */
    @Override
    public OrganizationEntity getRootOrganization() {
        Specification<OrganizationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(OrganizationEntity_.rootInd), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

    /**
     * @see OrganizationService#getDefaultOrganization()
     */
    @Override
    public OrganizationEntity getDefaultOrganization() {
        Specification<OrganizationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(OrganizationEntity_.defaultInd), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
