package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.OperatorEntity;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OperatorRepository extends BaseEntityRepository<OperatorEntity, Long> {
}
