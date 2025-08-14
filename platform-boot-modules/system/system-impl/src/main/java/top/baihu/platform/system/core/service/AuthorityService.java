package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.AuthorityEntity;

import java.util.List;

/**
 * @author elvea
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
