package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.cache.RoleCacheKeyGenerator;
import cn.elvea.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import cn.elvea.platform.system.core.mapper.RoleMapper;
import cn.elvea.platform.system.core.service.RoleAuthorityService;
import cn.elvea.platform.system.core.service.RoleService;
import cn.elvea.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see RoleService
 * @since 0.0.1
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
