package cc.elvea.platform.system.catalog.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.system.catalog.mapper.CatalogMapper;
import cc.elvea.platform.system.catalog.model.entity.CatalogEntity;
import cc.elvea.platform.system.catalog.service.CatalogService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG;

/**
 * @author elvea
 * @see CatalogService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG)
public class CatalogServiceImpl extends BaseEntityService<CatalogEntity, Long, CatalogMapper> implements CatalogService {
}
