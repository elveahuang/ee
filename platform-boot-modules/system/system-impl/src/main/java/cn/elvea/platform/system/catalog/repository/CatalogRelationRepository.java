package cn.elvea.platform.system.catalog.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface CatalogRelationRepository extends BaseEntityRepository<CatalogRelationEntity, Long> {
}
