package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum RateLimitTypeEnum implements BaseEnum<String> {
    DEFAULT("DEFAULT", "默认限流"),
    IP("IP", "IP限流"),
    CLUSTER("CLUSTER", "CLUSTER限流");

    private final String code;
    private final String description;

    RateLimitTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_rate_limit_type__".concat(this.code.toLowerCase());
    }

}
