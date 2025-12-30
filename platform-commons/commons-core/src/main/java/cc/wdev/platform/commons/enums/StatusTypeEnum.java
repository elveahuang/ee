package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum StatusTypeEnum implements BaseEnum<Integer> {
    ON(1, "ON", "已发布"),
    OFF(0, "OFF", "未发布"),
    PENDING(2, "PENDING", "待发布");

    private final Integer value;
    private final String code;
    private final String description;
}
