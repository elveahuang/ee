package cc.elvea.platform.system.catalog.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.catalog.model.entity.CatalogEntity;
import cc.elvea.platform.system.catalog.repository.CatalogRepository;
import cc.elvea.platform.system.catalog.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class CatalogServiceImpl extends BaseCachingEntityService<CatalogEntity, Long, CatalogRepository> implements CatalogService {
}
