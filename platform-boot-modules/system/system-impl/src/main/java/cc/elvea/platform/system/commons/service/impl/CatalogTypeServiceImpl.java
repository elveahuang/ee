package cc.elvea.platform.system.commons.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.commons.model.entity.CatalogTypeEntity;
import cc.elvea.platform.system.commons.repository.CatalogTypeRepository;
import cc.elvea.platform.system.commons.service.CatalogTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class CatalogTypeServiceImpl extends BaseCachingEntityService<CatalogTypeEntity, Long, CatalogTypeRepository> implements CatalogTypeService {
}
