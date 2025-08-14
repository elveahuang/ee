package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.OrderEntity;
import top.baihu.platform.system.mall.repository.OrderRepository;
import top.baihu.platform.system.mall.service.OrderService;

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
