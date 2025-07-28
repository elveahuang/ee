package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.JobEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface JobRepository extends BaseEntityRepository<JobEntity, Long> {
}
