package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.dto.OrganizationDeleteDto;
import top.baihu.platform.system.core.domain.dto.OrganizationDto;
import top.baihu.platform.system.core.domain.dto.OrganizationSaveDto;
import top.baihu.platform.system.core.domain.entity.OrganizationEntity;

/**
 * @author elvea
 */
public interface OrganizationService extends CachingEntityService<OrganizationEntity, Long> {

    /**
     * 保存部门
     */
    OrganizationDto saveOrganization(OrganizationSaveDto saveDto);

    /**
     * 删除部门
     */
    void deleteOrganization(OrganizationDeleteDto deleteDto);

    /**
     * 获取顶层部门
     */
    OrganizationEntity getRootOrganization();

    /**
     * 获取默认部门
     */
    OrganizationEntity getDefaultOrganization();

}
