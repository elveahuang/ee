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
public enum OrderSourceEnum implements BaseBizTypeEnum<Integer> {
    WEB(1, "WEB", "WEB"),
    APP(2, "APP", "APP");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MESSAGE_SOURCE_TYPE.getValue();
    }

}
