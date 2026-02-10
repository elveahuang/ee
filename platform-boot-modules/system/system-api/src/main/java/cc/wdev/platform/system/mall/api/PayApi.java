package cc.wdev.platform.system.mall.api;

import cc.wdev.platform.system.mall.domain.vo.PayTypeVo;

import java.util.List;

/**
 * @author elvea
 */
public interface PayApi {

    /**
     * 获取当前可用支付方式
     */
    List<PayTypeVo> getPayTypeList();

}
