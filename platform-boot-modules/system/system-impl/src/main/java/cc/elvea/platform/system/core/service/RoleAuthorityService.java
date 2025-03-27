package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface RoleAuthorityService extends CachingEntityService<RoleAuthorityEntity, Long> {

    /**
     * @param roleIdList 角色ID
     * @return 角色权限关联
     */
    List<RoleAuthorityEntity> findByRoleId(List<Long> roleIdList);

    /**
     * @param roleIdList 角色ID
     * @return 权限ID
     */
    List<Long> findAuthorityIdByRoleId(List<Long> roleIdList);

}
