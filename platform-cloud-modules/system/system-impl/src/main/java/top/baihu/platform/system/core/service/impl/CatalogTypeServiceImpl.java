package top.baihu.platform.system.core.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseEntityService;
import top.baihu.platform.system.core.domain.entity.CatalogTypeEntity;
import top.baihu.platform.system.core.mapper.CatalogTypeMapper;
import top.baihu.platform.system.core.service.CatalogTypeService;

import static top.baihu.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG_TYPE;

/**
 * @author elvea
 * @see CatalogTypeService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG_TYPE)
public class CatalogTypeServiceImpl extends BaseEntityService<CatalogTypeEntity, Long, CatalogTypeMapper> implements CatalogTypeService {
}
