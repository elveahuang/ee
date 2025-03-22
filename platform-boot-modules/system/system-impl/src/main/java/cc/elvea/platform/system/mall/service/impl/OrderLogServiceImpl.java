package cc.elvea.platform.system.mall.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.mall.model.entity.OrderLogEntity;
import cc.elvea.platform.system.mall.repository.OrderLogRepository;
import cc.elvea.platform.system.mall.service.OrderLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
