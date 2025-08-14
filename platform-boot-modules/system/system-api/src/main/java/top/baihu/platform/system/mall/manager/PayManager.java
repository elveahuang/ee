package top.baihu.platform.system.mall.manager;

import top.baihu.platform.system.mall.domain.vo.PayTypeVo;

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
