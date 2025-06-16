package cc.elvea.platform.lxp.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.lxp.commons.constants.LxpCacheConstants;
import cc.elvea.platform.lxp.core.model.entity.ProjectEntity;
import cc.elvea.platform.lxp.core.repository.ProjectRepository;
import cc.elvea.platform.lxp.core.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ProjectService
 * @see BaseCachingEntityService
 */
@Service
public class ProjectServiceImpl extends BaseCachingEntityService<ProjectEntity, Long, ProjectRepository> implements ProjectService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(LxpCacheConstants.PROJECT).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
