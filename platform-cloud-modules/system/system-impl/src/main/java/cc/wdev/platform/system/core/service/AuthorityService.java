package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.entity.AuthorityEntity;

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
