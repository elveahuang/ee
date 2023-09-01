package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.cache.UserRoleCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.UserRoleEntity;
import cn.elvea.platform.system.core.repository.UserRoleRepository;
import cn.elvea.platform.system.core.service.UserRoleService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see UserRoleService
 * @see BaseCachingEntityService
 * @since 0.0.1
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
