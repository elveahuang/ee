package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.mall.domain.entity.AccountVipLogEntity;
import top.baihu.platform.system.mall.repository.AccountVipLogRepository;
import top.baihu.platform.system.mall.service.AccountVipLogService;

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
