package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.commons.enums.EntityRelationTypeEnum;
import cc.elvea.platform.system.core.model.converter.OrganizationConverter;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cc.elvea.platform.system.core.model.dto.OrganizationDeleteDto;
import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity_;
import cc.elvea.platform.system.core.repository.OrganizationRepository;
import cc.elvea.platform.system.core.service.EntityRelationService;
import cc.elvea.platform.system.core.service.OrganizationService;
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
                .relationType(EntityRelationTypeEnum.ORG_PARENT_ORG.getValue())
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
