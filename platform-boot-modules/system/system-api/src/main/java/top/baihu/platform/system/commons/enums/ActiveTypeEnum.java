package top.baihu.platform.system.commons.enums;

import lombok.Getter;
import top.baihu.platform.commons.enums.BaseEnum;

/**
 * 启用类型
 *
 * @author elvea
 */
@Getter
public enum ActiveTypeEnum implements BaseEnum<Boolean> {
    ACTIVE(Boolean.TRUE, "ACTIVE", "启用"),
    INACTIVE(Boolean.FALSE, "INACTIVE", "禁用");

    private final Boolean value;
    private final String code;
    private final String description;

    ActiveTypeEnum(final Boolean value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    public static ActiveTypeEnum getActiveType(String code) {
        ActiveTypeEnum[] ts = ActiveTypeEnum.values();
        for (ActiveTypeEnum t : ts) {
            if (t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return ACTIVE;
    }

    @Override
    public String getLabel() {
        return ("label_active_type__".concat(this.code)).toLowerCase();
    }

}
