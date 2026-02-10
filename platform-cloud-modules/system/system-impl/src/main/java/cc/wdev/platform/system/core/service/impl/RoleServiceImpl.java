package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKey;
import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.cache.RoleCacheKeyGenerator;
import cc.wdev.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import cc.wdev.platform.system.core.mapper.RoleMapper;
import cc.wdev.platform.system.core.service.RoleAuthorityService;
import cc.wdev.platform.system.core.service.RoleService;
import cc.wdev.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see RoleService
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseCachingEntityService<RoleEntity, Long, RoleMapper> implements RoleService {

    private final RoleCacheKeyGenerator cacheKeyGenerator = new RoleCacheKeyGenerator();

    private final RoleAuthorityService roleAuthorityService;

    private final UserRoleService userRoleService;

    public RoleServiceImpl(RoleAuthorityService roleAuthorityService, UserRoleService userRoleService) {
        this.roleAuthorityService = roleAuthorityService;
        this.userRoleService = userRoleService;
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 扩展覆盖父类方法
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public RoleCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 实现服务接口方法
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * @see RoleService#findRoleByUserId(Long)
     */
    @Override
    public List<RoleEntity> findRoleByUserId(Long userId) {
        //
        CacheKey userRoleCacheKey = new UserRoleCacheKeyGenerator().key(userId);
        //
        List<RoleEntity> roleEntityList = Lists.newArrayList();
        List<Long> roleIdList = getCacheService().get(userRoleCacheKey, k -> {
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
