package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限业务类型
 * 租户权限 - 针对租户设置的系统功能集合
 * 会员权限 - 针对账号设置的系统功能集合
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AuthorityBizTypeEnum implements BaseBizTypeEnum<String> {
    TENANT("TENANT", "租户套餐权限菜单"),
    MEMBER("MEMBER", "会员套餐权限菜单");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.AUTHORITY_TYPE.getValue().toUpperCase();
    }

}
