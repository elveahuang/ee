package cc.wdev.platform.system.mall.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员单位类型枚举
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum VipItemDataValueEnum implements BaseBizTypeEnum<Integer> {
    DAY(0, "DAY", "天"),
    MONTH(1, "MONTH", "月"),
    YEAR(2, "YEAR", "年");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.VIP_ITEM_DATA_VALUE_TYPE.getValue();
    }

}
