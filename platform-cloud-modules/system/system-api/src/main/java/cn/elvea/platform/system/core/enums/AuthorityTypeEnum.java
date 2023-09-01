package cn.elvea.platform.system.core.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import lombok.Getter;

/**
 * 权限类型
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum AuthorityTypeEnum implements BaseEnum<String> {
    MENU("MENU", "菜单"),
    RESOURCE("RESOURCE", "资源");

    private final String code;
    private final String description;

    AuthorityTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static AuthorityTypeEnum getAuthorityType(String code) {
        AuthorityTypeEnum[] ts = AuthorityTypeEnum.values();
        for (AuthorityTypeEnum t : ts) {
            if (t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return RESOURCE;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_authority_type__" + this.code.toLowerCase();
    }

}
