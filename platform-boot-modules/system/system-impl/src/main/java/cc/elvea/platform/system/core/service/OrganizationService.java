package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.dto.OrganizationDeleteDto;
import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;

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
