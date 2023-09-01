package cn.elvea.platform.system.log.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface CaptchaLogRepository extends BaseEntityRepository<CaptchaLogEntity, Long> {
}
