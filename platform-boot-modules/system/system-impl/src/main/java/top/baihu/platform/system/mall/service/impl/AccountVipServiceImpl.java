package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.commons.enums.ActiveTypeEnum;
import top.baihu.platform.system.mall.domain.entity.AccountVipEntity;
import top.baihu.platform.system.mall.repository.AccountVipRepository;
import top.baihu.platform.system.mall.service.AccountVipService;

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
        condition.setActive(ActiveTypeEnum.ACTIVE.getValue());
        Example<AccountVipEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
