package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.OrderPayEntity;
import top.baihu.platform.system.mall.repository.OrderPayRepository;
import top.baihu.platform.system.mall.service.OrderPayService;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderPayServiceImpl
    extends BaseCachingEntityService<OrderPayEntity, Long, OrderPayRepository>
    implements OrderPayService {
}
