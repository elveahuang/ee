package cn.elvea.platform.commons.core.enums;

import lombok.Getter;

/**
 * 限流类型
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum RateLimitTypeEnum implements BaseEnum<String> {

    IP("IP", "IP"),

    KEY("KEY", "KEY");

    private final String type;

    private final String description;

    RateLimitTypeEnum(final String type, final String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return "label_rate_limit__".concat(this.type.toLowerCase());
    }

}
