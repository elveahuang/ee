package top.baihu.platform.system.core.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseEntityService;
import top.baihu.platform.system.core.domain.entity.CatalogEntity;
import top.baihu.platform.system.core.mapper.CatalogMapper;
import top.baihu.platform.system.core.service.CatalogService;

import static top.baihu.platform.system.commons.constants.SystemCacheConstants.CACHE_CATALOG;

/**
 * @author elvea
 * @see CatalogService
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG)
public class CatalogServiceImpl extends BaseEntityService<CatalogEntity, Long, CatalogMapper> implements CatalogService {
}
