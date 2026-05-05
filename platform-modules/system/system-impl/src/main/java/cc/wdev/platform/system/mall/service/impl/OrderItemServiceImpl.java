package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.OrderItemEntity;
import cc.wdev.platform.system.mall.repository.OrderItemRepository;
import cc.wdev.platform.system.mall.service.OrderItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderItemServiceImpl
    extends BaseCachingEntityService<OrderItemEntity, Long, OrderItemRepository>
    implements OrderItemService {
}
