package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.AccountVipLogEntity;
import cc.wdev.platform.system.mall.repository.AccountVipLogRepository;
import cc.wdev.platform.system.mall.service.AccountVipLogService;
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
