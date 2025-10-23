package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据来源类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SourceTypeEnum implements BaseEnum<Integer> {
    SYSTEM(1, "SYSTEM", "内置"),
    NORMAL(2, "NORMAL", "普通"),
    IMP(3, "IMP", "导入"),
    SYNC(4, "SYNC", "同步");

    private final Integer value;
    private final String code;
    private final String description;
}
