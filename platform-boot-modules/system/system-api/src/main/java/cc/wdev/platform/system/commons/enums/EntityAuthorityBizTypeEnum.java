package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityAuthorityBizTypeEnum implements BaseBizTypeEnum<String> {
    PACKAGE("PACKAGE", "套餐-权限关联"),
    TENANT("TENANT", "租户-权限关联"),
    ROLE("ROLE", "角色-权限关联"),
    USER("USER", "用户-权限关联"),
    ACCOUNT("ACCOUNT", "账号-权限关联");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ENTITY_AUTHORITY_TYPE.getValue();
    }

}
