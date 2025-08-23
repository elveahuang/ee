package cc.wdev.platform.system.core.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 权限分组
 *
 * @author elvea
 */
@Getter
public enum AuthorityGroupTypeEnum implements BaseEnum<Integer> {
    PLATFORM(1, "PLATFORM", "平台"),
    SYSTEM(2, "SYSTEM", "系统");

    private final Integer value;
    private final String code;
    private final String description;

    AuthorityGroupTypeEnum(final Integer value, final String code, final String description) {
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
        return "label_authority_group_type__" + this.code.toLowerCase();
    }

}
