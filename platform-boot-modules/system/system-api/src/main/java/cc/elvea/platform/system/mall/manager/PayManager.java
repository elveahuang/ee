package cc.elvea.platform.system.mall.manager;

import cc.elvea.platform.system.mall.model.vo.PayTypeVo;

import java.util.List;

/**
 * @author elvea
 */
public interface PayManager {

    /**
     * 获取当前可用支付方式
     */
    List<PayTypeVo> getPayTypeList();

}
