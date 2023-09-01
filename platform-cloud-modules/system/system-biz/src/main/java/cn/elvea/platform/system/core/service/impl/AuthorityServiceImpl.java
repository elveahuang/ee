package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.cache.AuthorityCacheKeyGenerator;
import cn.elvea.platform.system.core.cache.UserAuthorityCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import cn.elvea.platform.system.core.mapper.AuthorityMapper;
import cn.elvea.platform.system.core.service.AuthorityService;
import cn.elvea.platform.system.core.service.RoleAuthorityService;
import cn.elvea.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AuthorityService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Slf4j
@Service
public class AuthorityServiceImpl extends BaseCachingEntityService<AuthorityEntity, Long, AuthorityMapper> implements AuthorityService {

    private final AuthorityCacheKeyGenerator cacheKeyGenerator;

    private final RoleAuthorityService roleAuthorityService;

    private final UserRoleService userRoleService;

    public AuthorityServiceImpl(RoleAuthorityService roleAuthorityService, UserRoleService userRoleService) {
        this.roleAuthorityService = roleAuthorityService;
        this.userRoleService = userRoleService;
        this.cacheKeyGenerator = new AuthorityCacheKeyGenerator();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 扩展覆盖父类方法
    // ------------------------------------------------------------------------------------------------------------------------

    @Override
    public AuthorityCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 实现服务接口方法
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * @see AuthorityService#findByUserId(Long)
     */
    @Override
    public List<AuthorityEntity> findByUserId(Long userId) {
        //
        List<AuthorityEntity> authorityList = Lists.newArrayList();
        List<Long> authorityIdList = getCacheService().get(UserAuthorityCacheKeyGenerator.keyByUserId(userId), k -> {
            List<Long> userRoleIdList = this.userRoleService.findRoleIdByUserId(userId);
            List<Long> userAuthorityIdList = this.roleAuthorityService.findAuthorityIdByRoleId(userRoleIdList);
            if (CollectionUtils.isNotEmpty(userAuthorityIdList)) {
                authorityList.addAll(this.findByIds(userAuthorityIdList));
            }
            return userAuthorityIdList;
        });
        //
        if (CollectionUtils.isNotEmpty(authorityList)) {
            authorityList.forEach(this::setCache);
            return authorityList;
        } else {
            return findCacheByIds(authorityIdList);
        }
    }

}
