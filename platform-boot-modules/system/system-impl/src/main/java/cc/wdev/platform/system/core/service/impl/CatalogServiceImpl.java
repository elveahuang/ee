package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.CatalogEntity;
import cc.wdev.platform.system.core.repository.CatalogRepository;
import cc.wdev.platform.system.core.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class CatalogServiceImpl extends BaseCachingEntityService<CatalogEntity, Long, CatalogRepository> implements CatalogService {
}
