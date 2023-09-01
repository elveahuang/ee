package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.exception.SystemException;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.commons.constants.SystemConstants;
import cn.elvea.platform.system.core.cache.OrganizationCacheKeyGenerator;
import cn.elvea.platform.system.core.cache.UserOrganizationCacheKeyGenerator;
import cn.elvea.platform.system.core.model.converter.OrganizationConverter;
import cn.elvea.platform.system.core.model.dto.EntityRelationSaveDto;
import cn.elvea.platform.system.core.model.dto.OrganizationDto;
import cn.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;
import cn.elvea.platform.system.core.mapper.OrganizationMapper;
import cn.elvea.platform.system.core.service.EntityRelationService;
import cn.elvea.platform.system.core.service.OrganizationService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see OrganizationService
 * @since 0.0.1
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
        List<OrganizationEntity> organizationEntityList = lambdaQuery().eq(OrganizationEntity::getRootInd, Boolean.TRUE).list();
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
        List<OrganizationEntity> organizationEntityList = lambdaQuery().eq(OrganizationEntity::getDefaultInd, Boolean.TRUE).list();
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
        OrganizationEntity entity = OrganizationConverter.INSTANCE.saveDtoToEntity(saveDto);
        this.save(entity);

        // 保存组织父子关联
        EntityRelationSaveDto relationSaveDto = EntityRelationSaveDto.builder()
                .relationType(SystemConstants.ERN_ORG_PARENT_ORG)
                .entityId(entity.getId())
                .ancestorIdList(Lists.newArrayList(saveDto.getParentId()))
                .build();
        this.entityRelationService.saveEntityRelation(relationSaveDto);

        return OrganizationConverter.INSTANCE.entityToDto(entity);
    }

}
