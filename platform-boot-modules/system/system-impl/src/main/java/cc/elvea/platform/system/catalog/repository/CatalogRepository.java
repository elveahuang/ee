package cc.elvea.platform.system.catalog.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.catalog.model.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface CatalogRepository extends BaseEntityRepository<CatalogEntity, Long> {
}
