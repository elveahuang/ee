package cc.wdev.platform.system.mall.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.AccountVipEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface AccountVipService extends CachingEntityService<AccountVipEntity, Long> {

    /**
     * 获取账号会员信息
     */
    List<AccountVipEntity> getAccountVip(Long accountId);

}
