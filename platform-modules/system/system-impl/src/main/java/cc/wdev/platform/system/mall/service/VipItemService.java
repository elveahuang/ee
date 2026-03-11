package cc.wdev.platform.system.mall.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.VipItemEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface VipItemService extends CachingEntityService<VipItemEntity, Long> {

    /**
     * 获取指定会员类型下属的套餐信息
     */
    List<VipItemEntity> getVipItemByVipType(Long vipTypeId);

}
