package cc.elvea.platform.system.mall.manager;

import cc.elvea.platform.system.mall.model.vo.VipTypeVo;

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
