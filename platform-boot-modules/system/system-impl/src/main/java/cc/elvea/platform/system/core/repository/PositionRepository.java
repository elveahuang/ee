package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.PositionEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface PositionRepository extends BaseEntityRepository<PositionEntity, Long> {
}
