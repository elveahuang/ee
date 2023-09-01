package cn.elvea.platform.system.catalog.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.catalog.model.entity.CatalogEntity;
import cn.elvea.platform.system.catalog.repository.CatalogRepository;
import cn.elvea.platform.system.catalog.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class CatalogServiceImpl extends BaseCachingEntityService<CatalogEntity, Long, CatalogRepository> implements CatalogService {
}
