package cn.elvea.platform.system.catalog.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseEntityService;
import cn.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import cn.elvea.platform.system.catalog.mapper.CatalogTypeMapper;
import cn.elvea.platform.system.catalog.service.CatalogTypeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG_TYPE;

/**
 * @author elvea
 * @see CatalogTypeService
 * @since 0.0.1
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG_TYPE)
public class CatalogTypeServiceImpl extends BaseEntityService<CatalogTypeEntity, Long, CatalogTypeMapper> implements CatalogTypeService {
}
