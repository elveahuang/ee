package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.OrderTypeEntity;
import cc.wdev.platform.system.mall.repository.OrderTypeRepository;
import cc.wdev.platform.system.mall.service.OrderTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderTypeServiceImpl
    extends BaseCachingEntityService<OrderTypeEntity, Long, OrderTypeRepository>
    implements OrderTypeService {
}
