package cc.elvea.platform.system.catalog.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface CatalogTypeRepository extends BaseEntityRepository<CatalogTypeEntity, Long> {
}
