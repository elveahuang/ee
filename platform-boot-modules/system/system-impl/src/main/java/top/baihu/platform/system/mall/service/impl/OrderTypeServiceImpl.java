package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.OrderTypeEntity;
import top.baihu.platform.system.mall.repository.OrderTypeRepository;
import top.baihu.platform.system.mall.service.OrderTypeService;

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
