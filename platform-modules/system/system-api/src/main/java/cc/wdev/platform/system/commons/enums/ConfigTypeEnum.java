package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统配置项类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ConfigTypeEnum implements BaseBizTypeEnum<Integer> {
    TEXT(1, "TEXT", "单行文本"),
    TEXTAREA(2, "TEXTAREA", "多行文本"),
    EDITOR(3, "EDITOR", "编辑器"),
    DATETIME(4, "DATETIME", "日期时间"),
    DATE(5, "DATE", "日期"),
    TIME(6, "TIME", "时间");

    private final Integer value;
    private final String code;
    private final String description;


    @Override
    public String getGroup() {
        return BizGroupTypeEnum.CONFIG_TYPE.getValue().toUpperCase();
    }

}
