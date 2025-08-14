package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.OrderItemEntity;
import top.baihu.platform.system.mall.repository.OrderItemRepository;
import top.baihu.platform.system.mall.service.OrderItemService;

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
