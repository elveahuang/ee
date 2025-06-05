package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.cache.AuthorityCacheKeyGenerator;
import cc.elvea.platform.system.core.cache.UserAuthorityCacheKeyGenerator;
import cc.elvea.platform.system.core.mapper.AuthorityMapper;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
import cc.elvea.platform.system.core.service.AuthorityService;
import cc.elvea.platform.system.core.service.RoleAuthorityService;
import cc.elvea.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AuthorityService
 * @see BaseCachingEntityService
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
