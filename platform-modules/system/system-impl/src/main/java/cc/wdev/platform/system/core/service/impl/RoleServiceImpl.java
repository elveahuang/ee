package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import cc.wdev.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import cc.wdev.platform.system.core.repository.RoleRepository;
import cc.wdev.platform.system.core.service.RoleService;
import cc.wdev.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see RoleService
 * @see BaseCachingEntityService
 */
@Service
public class RoleServiceImpl extends BaseCachingEntityService<RoleEntity, Long, RoleRepository> implements RoleService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ROLE).build();

    private final UserRoleService userRoleService;

    public RoleServiceImpl(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see RoleService#findByUserId(Long)
     */
    @Override
    public List<RoleEntity> findByUserId(Long userId) {
        //
        List<RoleEntity> roleEntityList = Lists.newArrayList();
        List<Long> roleIdList = getCacheService().get(UserRoleCacheKeyGenerator.keyByUserId(userId), k -> {
            List<Long> userRoleIdList = this.userRoleService.findRoleIdByUserId(userId);
            if (CollectionUtils.isNotEmpty(userRoleIdList)) {
                roleEntityList.addAll(this.findByIds(userRoleIdList));
            }
            return userRoleIdList;
        });
        //
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            roleEntityList.forEach(this::setCache);
            return roleEntityList;
        } else {
            return findCacheByIds(roleIdList);
        }
    }

}
