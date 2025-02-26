package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.core.cache.RoleAuthorityCacheKeyGenerator;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity_;
import cc.elvea.platform.system.core.repository.RoleAuthorityRepository;
import cc.elvea.platform.system.core.service.RoleAuthorityService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see RoleAuthorityService
 * @see BaseCachingEntityService
 */
@Service
public class RoleAuthorityServiceImpl extends BaseCachingEntityService<RoleAuthorityEntity, Long, RoleAuthorityRepository>
        implements RoleAuthorityService {

    private final CacheKeyGenerator cacheKeyGenerator = new RoleAuthorityCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see RoleAuthorityService#findByRoleId(List)
     */
    @Override
    public List<RoleAuthorityEntity> findByRoleId(List<Long> roleIdList) {
        List<RoleAuthorityEntity> entityList = null;
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            Specification<RoleAuthorityEntity> specification = (root, query, builder) ->
                    builder.and(root.get(RoleAuthorityEntity_.ROLE_ID).in(roleIdList));
            entityList = this.repository.findAll(specification);
        }
        return CollectionUtils.isNotEmpty(entityList) ? entityList : Collections.emptyList();
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
