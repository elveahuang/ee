package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;

import java.util.List;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AuthorityService extends CachingEntityService<AuthorityEntity, Long> {

    /**
     * 获取指定用户所有的权限
     *
     * @param userId 用户ID
     * @return 权限
     */
    List<AuthorityEntity> findByUserId(Long userId);

}
