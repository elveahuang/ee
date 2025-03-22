package cc.elvea.platform.system.mall.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.mall.model.entity.AccountVipLogEntity;
import cc.elvea.platform.system.mall.repository.AccountVipLogRepository;
import cc.elvea.platform.system.mall.service.AccountVipLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountVipLogServiceImpl
        extends BaseCachingEntityService<AccountVipLogEntity, Long, AccountVipLogRepository>
        implements AccountVipLogService {
}
