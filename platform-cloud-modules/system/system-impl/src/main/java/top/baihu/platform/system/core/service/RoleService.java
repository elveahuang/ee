package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.entity.RoleEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface RoleService extends CachingEntityService<RoleEntity, Long> {

    /**
     * 根据用户ID获取用户所有角色
     *
     * @param userId 用户ID
     * @return 用户所有角色
     */
    List<RoleEntity> findRoleByUserId(Long userId);

}
