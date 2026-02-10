package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import cc.wdev.platform.system.mall.enums.OrderStatusTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum PayStatusTypeEnum implements BaseEnum<String> {
    PENDING_PAY("PENDING_PAY", "待支付", OrderStatusTypeEnum.PENDING_PAY),
    PENDING_CONFIRM("PENDING_CONFIRM", "待确认", OrderStatusTypeEnum.PENDING_CONFIRM),
    PAYED("PAYED", "已支付", OrderStatusTypeEnum.PAID),
    REFUND("REFUND", "已退款", OrderStatusTypeEnum.REFUND);

    private final String value;
    private final String description;
    private final OrderStatusTypeEnum orderStatus;

    public static OrderStatusTypeEnum getOrderStatus(String value) {
        PayStatusTypeEnum[] ts = PayStatusTypeEnum.values();
        for (PayStatusTypeEnum t : ts) {
            if (t.getValue().equalsIgnoreCase(value)) {
                return t.orderStatus;
            }
        }
        return PENDING_PAY.getOrderStatus();
    }

}
