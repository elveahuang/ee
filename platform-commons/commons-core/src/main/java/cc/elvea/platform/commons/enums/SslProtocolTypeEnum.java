package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum SslProtocolTypeEnum implements BaseEnum<String> {
    SSL("SSL", "SSL"),
    STARTTLS("STARTTLS", "STARTTLS");

    private final String code;
    private final String description;

    SslProtocolTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_lang__".concat(this.code.toLowerCase());
    }

}
