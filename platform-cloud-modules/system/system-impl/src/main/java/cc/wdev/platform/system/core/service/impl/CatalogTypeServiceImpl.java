package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.CatalogTypeEntity;
import cc.wdev.platform.system.core.mapper.CatalogTypeMapper;
import cc.wdev.platform.system.core.service.CatalogTypeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG_TYPE;

/**
 * @author elvea
 * @see CatalogTypeService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG_TYPE)
public class CatalogTypeServiceImpl extends BaseEntityService<CatalogTypeEntity, Long, CatalogTypeMapper> implements CatalogTypeService {
}
