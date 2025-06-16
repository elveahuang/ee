package cc.elvea.platform.system.mall.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.mall.model.entity.OrderPayEntity;
import cc.elvea.platform.system.mall.repository.OrderPayRepository;
import cc.elvea.platform.system.mall.service.OrderPayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
