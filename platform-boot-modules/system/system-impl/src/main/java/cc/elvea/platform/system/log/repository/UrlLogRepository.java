package cc.elvea.platform.system.log.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.log.model.entity.UrlLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface UrlLogRepository extends BaseEntityRepository<UrlLogEntity, Long> {
}
