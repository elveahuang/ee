package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.system.core.domain.entity.CatalogTypeEntity;
import cc.elvea.platform.system.core.mapper.CatalogTypeMapper;
import cc.elvea.platform.system.core.service.CatalogTypeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cc.elvea.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG_TYPE;

/**
 * @author elvea
 * @see CatalogTypeService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG_TYPE)
public class CatalogTypeServiceImpl extends BaseEntityService<CatalogTypeEntity, Long, CatalogTypeMapper> implements CatalogTypeService {
}
