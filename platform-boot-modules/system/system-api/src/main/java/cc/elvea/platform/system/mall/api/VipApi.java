package cc.elvea.platform.system.mall.api;

import cc.elvea.platform.system.mall.model.vo.VipTypeVo;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface VipApi {

    /**
     * 获取当前会员类型
     */
    List<VipTypeVo> getTypeList();

}
