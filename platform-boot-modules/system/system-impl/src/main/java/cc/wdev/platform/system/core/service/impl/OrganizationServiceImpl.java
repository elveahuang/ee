package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import cc.wdev.platform.system.commons.enums.EntityRelationBizTypeEnum;
import cc.wdev.platform.system.core.domain.converter.OrganizationConverter;
import cc.wdev.platform.system.core.domain.dto.EntityRelationSaveDto;
import cc.wdev.platform.system.core.domain.dto.OrganizationDeleteDto;
import cc.wdev.platform.system.core.domain.dto.OrganizationDto;
import cc.wdev.platform.system.core.domain.dto.OrganizationSaveDto;
import cc.wdev.platform.system.core.domain.entity.OrganizationEntity;
import cc.wdev.platform.system.core.domain.entity.OrganizationEntity_;
import cc.wdev.platform.system.core.repository.OrganizationRepository;
import cc.wdev.platform.system.core.service.EntityRelationService;
import cc.wdev.platform.system.core.service.OrganizationService;
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
        OrganizationEntity entity = SpringUtils.getBean(OrganizationConverter.class).saveDto2Entity(saveDto);

        // 保存部门基本信息
        this.save(entity);

        // 保存部门关联信息
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
            .relationType(EntityRelationBizTypeEnum.ORG_PARENT_ORG.getValue())
            .ancestorIdList(Collections.singletonList(saveDto.getParentId()))
            .entityId(entity.getId())
            .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return SpringUtils.getBean(OrganizationConverter.class).entity2Dto(entity);
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
            predicates.add(builder.equal(root.get(OrganizationEntity_.ROOT_IND), Boolean.TRUE));
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
            predicates.add(builder.equal(root.get(OrganizationEntity_.DEFAULT_IND), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
