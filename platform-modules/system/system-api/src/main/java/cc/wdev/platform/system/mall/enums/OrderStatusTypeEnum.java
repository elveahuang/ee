package cc.wdev.platform.system.mall.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum OrderStatusTypeEnum implements BaseBizTypeEnum<Integer> {
    PENDING_PAY(0, "PENDING_PAY", "待支付"),
    PENDING_CONFIRM(1, "PENDING_CONFIRM", "待确认"),
    PAID(2, "PAID", "已付款"),
    SHIPPED(3, "SHIPPED", "已发货"),
    COMPLETED(4, "COMPLETED", "已完成"),
    CANCELED(5, "CANCELED", "已取消"),
    REFUND(6, "REFUND", "已退款");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ORDER_STATUS_TYPE.getValue();
    }

}
