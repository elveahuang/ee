package cc.elvea.platform.system.catalog.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface CatalogRelationRepository extends BaseEntityRepository<CatalogRelationEntity, Long> {
}
