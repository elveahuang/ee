package cc.wdev.platform.system.mall.api;

import cc.wdev.platform.system.mall.domain.vo.VipTypeVo;

import java.util.List;

/**
 * @author elvea
 */
public interface VipApi {

    /**
     * 获取当前会员类型
     */
    List<VipTypeVo> getTypeList();

}
