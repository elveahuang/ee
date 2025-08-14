package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.OrderLogEntity;
import top.baihu.platform.system.mall.repository.OrderLogRepository;
import top.baihu.platform.system.mall.service.OrderLogService;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderLogServiceImpl
    extends BaseCachingEntityService<OrderLogEntity, Long, OrderLogRepository>
    implements OrderLogService {
}
