package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限分组
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AuthorityGroupTypeEnum implements BaseBizTypeEnum<Integer> {
    SYSTEM(1, "SYSTEM", "系统，平台级别权限，包含平台运营相关等所有功能模块"),
    TENANT(2, "TENANT", "租户，系统级别权限，包含除了平台运营相关其他功能模块"),
    MEMBER(3, "MEMBER", "会员，用户级别权限");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.AUTHORITY_GROUP_TYPE.getValue();
    }

}
