package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum implements BaseEnum<Integer> {
    SYSTEM(1, "SYSTEM", "系统范围"),
    USER(2, "USER", "用户范围"),
    NONE(0, "NONE", "未指定");

    private final Integer value;
    private final String code;
    private final String description;
}
