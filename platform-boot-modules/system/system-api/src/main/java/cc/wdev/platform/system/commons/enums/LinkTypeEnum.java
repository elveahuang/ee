package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum LinkTypeEnum implements BaseEnum<String> {
    USER("user", "label_link_type__user", "用户主页短链接");

    private final String code;
    private final String label;
    private final String description;

    LinkTypeEnum(String code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

}
