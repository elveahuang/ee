package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.entity.RoleEntity;

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
