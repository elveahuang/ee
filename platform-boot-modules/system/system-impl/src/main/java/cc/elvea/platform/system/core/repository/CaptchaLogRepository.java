package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.CaptchaLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface CaptchaLogRepository extends BaseEntityRepository<CaptchaLogEntity, Long> {
}
