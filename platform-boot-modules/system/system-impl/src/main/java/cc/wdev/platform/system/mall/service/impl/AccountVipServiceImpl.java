package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.enums.ActiveTypeEnum;
import cc.wdev.platform.system.mall.domain.entity.AccountVipEntity;
import cc.wdev.platform.system.mall.repository.AccountVipRepository;
import cc.wdev.platform.system.mall.service.AccountVipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountVipServiceImpl
    extends BaseCachingEntityService<AccountVipEntity, Long, AccountVipRepository>
    implements AccountVipService {

    /**
     * @see AccountVipService#getAccountVip(Long)
     */
    @Override
    public List<AccountVipEntity> getAccountVip(Long accountId) {
        AccountVipEntity condition = AccountVipEntity.builder().build();
        condition.setActive(ActiveTypeEnum.ENABLED.getValue());
        Example<AccountVipEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
