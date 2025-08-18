package cc.wdev.platform.system.mall.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.mall.domain.entity.VipTypeEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface VipTypeService extends CachingEntityService<VipTypeEntity, Long> {

    /**
     * 获取当前可用会员类型
     */
    List<VipTypeEntity> getTypeList();

}
