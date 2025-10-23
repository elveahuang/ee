package cc.wdev.platform.system.mall.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单来源
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum OrderTypeEnum implements BaseBizTypeEnum<Integer> {
    VIP(1, "VIP", "会员订单"),
    MALL(2, "MALL", "商城订单");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ORDER_TYPE.getValue();
    }

}
