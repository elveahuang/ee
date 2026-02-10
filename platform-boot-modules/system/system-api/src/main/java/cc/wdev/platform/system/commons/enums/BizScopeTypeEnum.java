package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务范围类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum BizScopeTypeEnum implements BaseEnum<Integer> {
    SYSTEM(1, "SYSTEM", "平台范围，只允许平台使用，即顶层租户专属，不允许下放到其他租户"),
    TENANT(2, "TENANT", "系统范围，直接在页面添加的记录，一般情况下允许直接删除"),
    USER(3, "USER", "用户范围，直接在页面添加的记录，一般情况下允许直接删除"),
    ACCOUNT(4, "ACCOUNT", "账号范围，直接在页面添加的记录，一般情况下允许直接删除");

    private final Integer value;
    private final String code;
    private final String description;
}
