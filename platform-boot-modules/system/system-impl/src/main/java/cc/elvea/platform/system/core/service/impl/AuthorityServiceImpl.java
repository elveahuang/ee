package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import cc.elvea.platform.system.core.cache.UserAuthorityCacheKeyGenerator;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
import cc.elvea.platform.system.core.repository.AuthorityRepository;
import cc.elvea.platform.system.core.service.AuthorityService;
import cc.elvea.platform.system.core.service.RoleAuthorityService;
import cc.elvea.platform.system.core.service.UserRoleService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AuthorityService
 * @see BaseCachingEntityService
 */
@Service
public class AuthorityServiceImpl extends BaseCachingEntityService<AuthorityEntity, Long, AuthorityRepository>
        implements AuthorityService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.AUTHORITY).build();

    private final RoleAuthorityService roleAuthorityService;

    private final UserRoleService userRoleService;

    public AuthorityServiceImpl(RoleAuthorityService roleAuthorityService, UserRoleService userRoleService) {
        this.roleAuthorityService = roleAuthorityService;
        this.userRoleService = userRoleService;
    }

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see AuthorityService#findByUserId(Long)
     */
    @Override
    public List<AuthorityEntity> findByUserId(Long userId) {
        List<AuthorityEntity> authorityList = Lists.newArrayList();
        List<Long> authorityIdList = getCacheService().get(UserAuthorityCacheKeyGenerator.keyByUserId(userId), k -> {
            List<Long> userRoleIdList = this.userRoleService.findRoleIdByUserId(userId);
            List<Long> userAuthorityIdList = this.roleAuthorityService.findAuthorityIdByRoleId(userRoleIdList);
            if (CollectionUtils.isNotEmpty(userAuthorityIdList)) {
                authorityList.addAll(this.findByIds(userAuthorityIdList));
            }
            return userAuthorityIdList;
        });
        if (CollectionUtils.isNotEmpty(authorityList)) {
            authorityList.forEach(this::setCache);
            return authorityList;
        } else {
            return findCacheByIds(authorityIdList);
        }
    }

}
