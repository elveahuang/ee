package cc.elvea.platform.system.mall.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.mall.model.entity.OrderItemEntity;
import cc.elvea.platform.system.mall.repository.OrderItemRepository;
import cc.elvea.platform.system.mall.service.OrderItemService;
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
