package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.RoleAuthorityEntity;

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
