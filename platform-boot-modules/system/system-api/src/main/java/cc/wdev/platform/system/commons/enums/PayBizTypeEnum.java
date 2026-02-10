package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型
 * ALIPAY - 支付宝
 * WECHAT - 微信支付
 * USDT - USDT
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum PayBizTypeEnum implements BaseEnum<String> {
    ALIPAY("ALIPAY", "支付宝"),
    WECHAT("WECHAT", "微信支付"),
    USDT("USDT", "USDT");

    private final String value;
    private final String description;
}
