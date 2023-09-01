package cn.elvea.platform.lxp.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.lxp.commons.constants.LxpCacheConstants;
import cn.elvea.platform.lxp.core.model.entity.ProjectEntity;
import cn.elvea.platform.lxp.core.repository.ProjectRepository;
import cn.elvea.platform.lxp.core.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ProjectService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
public class ProjectServiceImpl extends BaseCachingEntityService<ProjectEntity, Long, ProjectRepository> implements ProjectService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(LxpCacheConstants.PROJECT).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
