package top.baihu.platform.system.mall.manager;

import top.baihu.platform.system.mall.domain.vo.VipTypeVo;

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
