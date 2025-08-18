package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.CatalogEntity;
import cc.wdev.platform.system.core.mapper.CatalogMapper;
import cc.wdev.platform.system.core.service.CatalogService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG;

/**
 * @author elvea
 * @see CatalogService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG)
public class CatalogServiceImpl extends BaseEntityService<CatalogEntity, Long, CatalogMapper> implements CatalogService {
}
