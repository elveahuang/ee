package cc.elvea.platform.system.log.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface OperationLogRepository extends BaseEntityRepository<OperationLogEntity, Long> {
}
