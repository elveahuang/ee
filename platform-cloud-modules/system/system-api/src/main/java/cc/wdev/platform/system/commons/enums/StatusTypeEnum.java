package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum StatusTypeEnum implements BaseEnum<Integer> {
    ENABLED(1, "ENABLED", "启用"),
    DISABLED(2, "DISABLED", "禁用");

    private final Integer value;
    private final String code;
    private final String description;

}
