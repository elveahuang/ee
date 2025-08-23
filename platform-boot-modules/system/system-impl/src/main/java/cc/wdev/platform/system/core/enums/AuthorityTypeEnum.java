package cc.wdev.platform.system.core.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 权限类型
 *
 * @author elvea
 */
@Getter
public enum AuthorityTypeEnum implements BaseEnum<Integer> {
    MODULE(1, "MODULE", "模块"),
    CATALOG(2, "CATALOG", "分组"),
    RESOURCE(3, "RESOURCE", "资源"),
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
