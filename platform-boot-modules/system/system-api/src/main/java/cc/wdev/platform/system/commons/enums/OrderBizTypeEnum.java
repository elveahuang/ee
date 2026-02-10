package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.biz.OrderTypeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static cc.wdev.platform.system.commons.constants.SystemMallConstants.DEFAULT_CONFIG;

/**
 * 订单类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum OrderBizTypeEnum implements BaseBizTypeEnum<String> {
    TENANT("TENANT", "租户订单", DEFAULT_CONFIG),
    MEMBER("MEMBER", "会员订单", DEFAULT_CONFIG);

    private final String value;
    private final String description;
    private final OrderTypeConfig config;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ORDER_TYPE.getValue().toUpperCase();
    }

}
