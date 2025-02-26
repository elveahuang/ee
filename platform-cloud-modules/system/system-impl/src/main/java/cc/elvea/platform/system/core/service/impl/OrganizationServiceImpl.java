package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.exception.SystemException;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.commons.constants.SystemConstants;
import cc.elvea.platform.system.core.cache.OrganizationCacheKeyGenerator;
import cc.elvea.platform.system.core.cache.UserOrganizationCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.OrganizationMapper;
import cc.elvea.platform.system.core.model.converter.OrganizationConverter;
import cc.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
import cc.elvea.platform.system.core.service.EntityRelationService;
import cc.elvea.platform.system.core.service.OrganizationService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see OrganizationService
 */
@Service
public class OrganizationServiceImpl extends BaseCachingEntityService<OrganizationEntity, Long, OrganizationMapper> implements OrganizationService {

    private final OrganizationCacheKeyGenerator cacheKeyGenerator = new OrganizationCacheKeyGenerator();

    private final EntityRelationService entityRelationService;

    public OrganizationServiceImpl(EntityRelationService entityRelationService) {
        this.entityRelationService = entityRelationService;
    }

    @Override
    public OrganizationCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see OrganizationService#getRootOrganization()
     */
    @Override
    public OrganizationEntity getRootOrganization() {
        List<OrganizationEntity> organizationEntityList = lambdaQueryWrapper().eq(OrganizationEntity::getRootInd, Boolean.TRUE).list();
        if (CollectionUtils.isNotEmpty(organizationEntityList)) {
            return organizationEntityList.get(0);
        }
        throw new SystemException("");
    }

    /**
     * @see OrganizationService#getDefaultOrganization()
     */
    @Override
    public OrganizationEntity getDefaultOrganization() {
        List<OrganizationEntity> organizationEntityList = lambdaQueryWrapper().eq(OrganizationEntity::getDefaultInd, Boolean.TRUE).list();
        if (CollectionUtils.isNotEmpty(organizationEntityList)) {
            return organizationEntityList.get(0);
        }
        return getRootOrganization();
    }

    /**
     * @see OrganizationService#findByUserId(Long)
     */
    @Override
    public List<OrganizationEntity> findByUserId(Long userId) {
        //
        CacheKey userOrganizationCacheKey = new UserOrganizationCacheKeyGenerator().key(userId);
        //
        List<OrganizationEntity> userOrganizationEntityList = Lists.newArrayList();
        List<Long> userOrganizationIdList = getCacheService().get(userOrganizationCacheKey, k -> {
            List<Long> organizationIdList = this.entityRelationService.getDirectOrganizationByUserId(userId);
            userOrganizationEntityList.addAll(this.findCacheByIds(organizationIdList));
            return userOrganizationEntityList.stream().map(OrganizationEntity::getId).collect(Collectors.toList());
        });
        //
        if (CollectionUtils.isNotEmpty(userOrganizationEntityList)) {
            userOrganizationEntityList.forEach(this::setCache);
        } else {
            userOrganizationEntityList.addAll(findCacheByIds(userOrganizationIdList));
        }
        return userOrganizationEntityList;
    }

    /**
     * @see OrganizationService#saveOrganization(OrganizationSaveDto)
     */
    @Override
    public OrganizationDto saveOrganization(OrganizationSaveDto saveDto) {
        if (!StringUtils.isNotEmpty(saveDto.getCode())) {
            saveDto.setCode(generateCode("ORG"));
        }

        // 保存组织基本信息
        OrganizationEntity entity = SpringUtils.getBean(OrganizationConverter.class).saveDtoToEntity(saveDto);
        this.save(entity);

        // 保存组织父子关联
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
                .relationType(SystemConstants.ERN_ORG_PARENT_ORG)
                .entityId(entity.getId())
                .ancestorIdList(Lists.newArrayList(saveDto.getParentId()))
                .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return SpringUtils.getBean(OrganizationConverter.class).entityToDto(entity);
    }

}
