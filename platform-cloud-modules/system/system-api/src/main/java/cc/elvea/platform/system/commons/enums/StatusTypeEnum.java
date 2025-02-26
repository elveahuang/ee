package cc.elvea.platform.system.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 状态类型
 *
 * @author elvea
 */
@Getter
public enum StatusTypeEnum implements BaseEnum<Integer> {
    ENABLED(1, "ENABLED", "启用"),
    DISABLED(2, "DISABLED", "禁用");

    private final Integer value;
    private final String code;
    private final String description;

    StatusTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return "label_status_type__" + this.code.toLowerCase();
    }

}
