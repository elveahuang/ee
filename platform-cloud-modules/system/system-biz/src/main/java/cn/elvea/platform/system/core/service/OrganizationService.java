package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.dto.OrganizationDto;
import cn.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 0.0.1
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
