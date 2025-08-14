package top.baihu.platform.system.mall.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.mall.domain.entity.PayTypeEntity;

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
