package cc.wdev.platform.system.mall.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.mall.domain.entity.PayTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface PayTypeRepository extends BaseEntityRepository<PayTypeEntity, Long> {
}
