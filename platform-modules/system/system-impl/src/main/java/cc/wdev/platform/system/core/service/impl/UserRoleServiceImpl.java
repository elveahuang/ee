package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.entity.UserRoleEntity;
import cc.wdev.platform.system.core.repository.UserRoleRepository;
import cc.wdev.platform.system.core.service.UserRoleService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see UserRoleService
 * @see BaseCachingEntityService
 */
@Service
public class UserRoleServiceImpl extends BaseCachingEntityService<UserRoleEntity, Long, UserRoleRepository> implements UserRoleService {

    private final CacheKeyGenerator cacheKeyGenerator = new UserRoleCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see UserRoleService#findByUserId(Long)
     */
    @Override
    public List<UserRoleEntity> findByUserId(Long userId) {
        UserRoleEntity example = UserRoleEntity.builder().userId(userId).build();
        List<UserRoleEntity> entities = this.repository.findAll(Example.of(example));
        return (CollectionUtils.isNotEmpty(entities)) ? entities : Collections.emptyList();
    }

    /**
     * @see UserRoleService#findRoleIdByUserId(Long)
     */
    @Override
    public List<Long> findRoleIdByUserId(Long userId) {
        return this.findByUserId(userId).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

}
