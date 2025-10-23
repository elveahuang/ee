package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
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
public enum PayBizTypeEnum implements BaseBizTypeEnum<String> {
    ALIPAY("ALIPAY", "支付宝"),
    WECHAT("WECHAT", "微信支付"),
    USDT("USDT", "USDT");

    private final String value;
    private final String description;

    PayBizTypeEnum(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.PAY_TYPE.getValue().toUpperCase();
    }

}
