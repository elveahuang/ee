package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.elvea.platform.system.core.model.entity.RoleEntity;
import cc.elvea.platform.system.core.repository.RoleRepository;
import cc.elvea.platform.system.core.service.RoleService;
import cc.elvea.platform.system.core.service.UserRoleService;
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
