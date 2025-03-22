package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum PayTypeEnum implements BaseEnum<String> {
    ALIPAY("ALIPAY", "支付宝"),
    WECHAT("WECHAT", "微信"),
    USDT("USDT", "USDT");

    private final String code;
    private final String description;

    PayTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_pay_type__" + this.code.toLowerCase();
    }

}
