package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;

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
