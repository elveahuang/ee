package cc.elvea.platform.system.mall.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.mall.model.entity.VipItemEntity;

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
