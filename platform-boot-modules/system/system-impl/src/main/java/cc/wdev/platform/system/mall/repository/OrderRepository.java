package cc.wdev.platform.system.mall.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.mall.domain.entity.OrderEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface OrderRepository extends BaseEntityRepository<OrderEntity, Long> {
}
