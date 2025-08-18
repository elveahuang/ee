package cc.wdev.platform.system.mall.manager;

import cc.wdev.platform.system.mall.domain.vo.VipTypeVo;

import java.util.List;

/**
 * @author elvea
 */
public interface VipManager {

    /**
     * 获取当前会员类型
     */
    List<VipTypeVo> getTypeList();

}
