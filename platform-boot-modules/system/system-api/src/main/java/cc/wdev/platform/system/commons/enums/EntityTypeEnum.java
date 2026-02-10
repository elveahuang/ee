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
public enum EntityTypeEnum implements BaseEnum<Long> {
    USER(1L, "USER", "用户体系"),
    ACCOUNT(2L, "ACCOUNT", "账号体系"),
    NONE(0L, "NONE", "未知身份");

    private final Long value;
    private final String code;
    private final String description;
}
