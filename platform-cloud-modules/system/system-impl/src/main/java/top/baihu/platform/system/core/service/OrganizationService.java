package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.dto.OrganizationDto;
import top.baihu.platform.system.core.domain.dto.OrganizationSaveDto;
import top.baihu.platform.system.core.domain.entity.OrganizationEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface OrganizationService extends CachingEntityService<OrganizationEntity, Long> {
    /**
     * 获取顶层组织
     *
     * @return 获取顶层组织
     */
    OrganizationEntity getRootOrganization();

    /**
     * 获取默认组织
     *
     * @return 获取默认组织
     */
    OrganizationEntity getDefaultOrganization();

    /**
     * 获取指定用户所属的组织
     *
     * @param userId 用户ID
     * @return 用户所属的组织
     */
    List<OrganizationEntity> findByUserId(Long userId);

    /**
     * 保存组织
     *
     * @param saveDto {@link OrganizationSaveDto}
     * @return {@link OrganizationDto}
     */
    OrganizationDto saveOrganization(OrganizationSaveDto saveDto);

}
