package cc.elvea.platform.system.catalog.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface CatalogRelationRepository extends BaseEntityRepository<CatalogRelationEntity, Long> {
}
