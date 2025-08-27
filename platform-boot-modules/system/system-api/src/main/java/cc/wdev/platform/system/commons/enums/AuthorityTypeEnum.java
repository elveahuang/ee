package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 权限类型
 *
 * @author elvea
 */
@Getter
public enum AuthorityTypeEnum implements BaseEnum<Integer> {
    SECTION(1, "SECTION", "版块"),
    GROUP(2, "GROUP", "分组"),
    MODULE(3, "MODULE", "模块"),
    PERMISSION(4, "PERMISSION", "权限");

    private final Integer value;
    private final String code;
    private final String description;

    AuthorityTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return "label_authority_type__" + this.code.toLowerCase();
    }

}
