package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
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
