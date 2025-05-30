package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.dto.RoleAuthoritySaveDto;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface RoleAuthorityService extends CachingEntityService<RoleAuthorityEntity, Long> {

    /**
     * @param authorityId 权限ID
     * @return 角色权限关联
     */
    List<RoleAuthorityEntity> findByAuthorityId(Long authorityId);

    /**
     * @param authorityId 权限ID
     * @return 角色ID
     */
    List<Long> findRoleIdByAuthorityId(Long authorityId);

    /**
     * @param authorityIdList 权限ID
     * @return 角色权限关联
     */
    List<RoleAuthorityEntity> findByAuthorityId(List<Long> authorityIdList);

    /**
     * @param authorityIdList 权限ID
     * @return 角色ID
     */
    List<Long> findRoleIdByAuthorityId(List<Long> authorityIdList);

    /**
     * @param roleId 角色ID
     * @return 角色权限关联
     */
    List<RoleAuthorityEntity> findByRoleId(Long roleId);

    /**
     * @param roleId 角色ID
     * @return 权限ID
     */
    List<Long> findAuthorityIdByRoleId(Long roleId);

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

    /**
     * 保存角色和权限关联
     *
     * @param saveDto {@link RoleAuthoritySaveDto}
     * @return boolean
     */
    boolean saveRoleAuthority(RoleAuthoritySaveDto saveDto);

    /**
     * 删除指定权限
     *
     * @param authorityIdList 权限ID
     * @return boolean
     */
    boolean deleteByAuthorityId(List<Long> authorityIdList);

}
