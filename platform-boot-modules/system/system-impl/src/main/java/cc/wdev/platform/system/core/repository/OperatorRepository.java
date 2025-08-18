package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.OperatorEntity;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OperatorRepository extends BaseEntityRepository<OperatorEntity, Long> {
}
