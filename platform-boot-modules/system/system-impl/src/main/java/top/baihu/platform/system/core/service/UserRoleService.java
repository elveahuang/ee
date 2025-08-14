package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.UserRoleEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface UserRoleService extends CachingEntityService<UserRoleEntity, Long> {

    /**
     * @param userId 用户ID
     * @return 用户角色关联
     */
    List<UserRoleEntity> findByUserId(Long userId);

    /**
     * @param userId 用户ID
     * @return 角色ID
     */
    List<Long> findRoleIdByUserId(Long userId);

}
