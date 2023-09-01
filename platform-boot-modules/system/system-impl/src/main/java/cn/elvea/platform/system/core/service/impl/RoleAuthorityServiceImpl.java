package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import cn.elvea.platform.system.core.repository.RoleAuthorityRepository;
import cn.elvea.platform.system.core.service.RoleAuthorityService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see RoleAuthorityService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
public class RoleAuthorityServiceImpl extends BaseCachingEntityService<RoleAuthorityEntity, Long, RoleAuthorityRepository>
        implements RoleAuthorityService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ROLE_AUTHORITY).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see RoleAuthorityService#findByRoleId(List)
     */
    @Override
    public List<RoleAuthorityEntity> findByRoleId(List<Long> roleIdList) {
        List<RoleAuthorityEntity> entities = null;
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            entities = this.repository.findAll((Specification<RoleAuthorityEntity>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(root.get("roleId").in(roleIdList));
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            });
        }
        return CollectionUtils.isNotEmpty(entities) ? entities : Collections.emptyList();
    }

    /**
     * @see RoleAuthorityService#findAuthorityIdByRoleId(List)
     */
    @Override
    public List<Long> findAuthorityIdByRoleId(List<Long> roleIdList) {
        return CollectionUtils.isNotEmpty(roleIdList) ?
                this.findByRoleId(roleIdList).stream().map(RoleAuthorityEntity::getAuthorityId).collect(Collectors.toList()) :
                Collections.emptyList();
    }

}
