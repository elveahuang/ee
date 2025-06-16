package cc.elvea.platform.system.mall.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.mall.model.entity.PayTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface PayTypeRepository extends BaseEntityRepository<PayTypeEntity, Long> {
}
