package cn.elvea.platform.system.catalog.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseEntityService;
import cn.elvea.platform.system.catalog.model.entity.CatalogEntity;
import cn.elvea.platform.system.catalog.mapper.CatalogMapper;
import cn.elvea.platform.system.catalog.service.CatalogService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG;

/**
 * @author elvea
 * @see CatalogService
 * @since 0.0.1
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG)
public class CatalogServiceImpl extends BaseEntityService<CatalogEntity, Long, CatalogMapper> implements CatalogService {
}
