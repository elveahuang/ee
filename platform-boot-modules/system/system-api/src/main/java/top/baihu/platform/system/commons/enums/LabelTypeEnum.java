package top.baihu.platform.system.commons.enums;

import lombok.Getter;
import top.baihu.platform.commons.enums.BaseEnum;

/**
 * @author elvea
 */
@Getter
public enum LabelTypeEnum implements BaseEnum<String> {
    PROPERTIES("properties", "messages_%s", "properties"),
    JSON("json", "%s", "json");

    private final String code;
    private final String filename;
    private final String description;

    LabelTypeEnum(final String code, final String filename, final String description) {
        this.code = code;
        this.filename = filename;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.getCode();
    }

    @Override
    public String getLabel() {
        return ("label__label_type__".concat(this.code)).toLowerCase();
    }

}
