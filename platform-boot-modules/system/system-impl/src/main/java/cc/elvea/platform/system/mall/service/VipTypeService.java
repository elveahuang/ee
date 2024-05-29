package cc.elvea.platform.system.mall.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.mall.model.entity.VipTypeEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface VipTypeService extends CachingEntityService<VipTypeEntity, Long> {

    /**
     * 获取当前可用会员类型
     */
    List<VipTypeEntity> getTypeList();

}
