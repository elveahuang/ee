package cc.elvea.platform.system.mall.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.mall.model.entity.OrderEntity;
import cc.elvea.platform.system.mall.repository.OrderRepository;
import cc.elvea.platform.system.mall.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl
        extends BaseCachingEntityService<OrderEntity, Long, OrderRepository>
        implements OrderService {
}
