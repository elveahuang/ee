package cc.elvea.platform.system.job.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.job.model.entity.JobEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface JobRepository extends BaseEntityRepository<JobEntity, Long> {
}
