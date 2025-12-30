package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 身份类型
 * ACCOUNT  - 账号体系，账号体系用于前台系统
 * USER     - 用户体系，用户体系用于后台系统
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityTypeEnum implements BaseEnum<Integer> {
    USER(1, "USER", "用户体系"),
    ACCOUNT(2, "ACCOUNT", "账号体系"),
    NONE(0, "NONE", "未知身份");

    private final Integer value;
    private final String code;
    private final String description;
}
