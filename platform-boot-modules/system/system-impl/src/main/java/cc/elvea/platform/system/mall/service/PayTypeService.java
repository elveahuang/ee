package cc.elvea.platform.system.mall.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.mall.model.entity.PayTypeEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface PayTypeService extends CachingEntityService<PayTypeEntity, Long> {

    /**
     * 获取当前可用支付方式
     */
    List<PayTypeEntity> getTypeList();

}
