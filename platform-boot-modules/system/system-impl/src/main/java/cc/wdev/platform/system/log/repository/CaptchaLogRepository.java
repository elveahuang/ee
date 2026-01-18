package cc.wdev.platform.system.log.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.log.domain.entity.CaptchaLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface CaptchaLogRepository extends BaseEntityRepository<CaptchaLogEntity, Long> {
}
