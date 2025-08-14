package top.baihu.platform.system.mall.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.mall.domain.entity.OrderItemEntity;

/**
 * @author elvea
 */
@Repository
public interface OrderItemRepository extends BaseEntityRepository<OrderItemEntity, Long> {
}
