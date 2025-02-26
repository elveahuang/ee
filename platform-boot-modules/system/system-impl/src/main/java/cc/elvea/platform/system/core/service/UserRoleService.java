package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.entity.UserRoleEntity;

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
