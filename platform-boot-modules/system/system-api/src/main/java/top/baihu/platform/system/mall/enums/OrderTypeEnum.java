package top.baihu.platform.system.mall.enums;

import lombok.Getter;
import top.baihu.platform.commons.enums.BaseEnum;

/**
 * 订单来源
 *
 * @author elvea
 */
@Getter
public enum OrderTypeEnum implements BaseEnum<Integer> {
    VIP(1, "VIP", "会员订单"),
    MALL(2, "MALL", "商城订单");

    private final Integer value;
    private final String code;
    private final String description;

    OrderTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_order_type__".concat(this.code)).toLowerCase();
    }

}
