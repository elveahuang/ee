package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 主体类型
 *
 * @author elvea
 */
@Getter
public enum SubjectTypeEnum implements BaseEnum<Integer> {
    ACCOUNT(1, "ACCOUNT", "账号"),
    USER(2, "USER", "用户");

    private final Integer value;
    private final String code;
    private final String description;

    SubjectTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_subject_type__".concat(this.code)).toLowerCase();
    }

}
