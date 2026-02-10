package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标签类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum TagBizTypeEnum implements BaseBizTypeEnum<String> {
    SYSTEM("SYSTEM", "系统标签"),
    USER("USER", "用户标签"),
    NONE("NONE", "未指定");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.TAG_TYPE.getValue().toUpperCase();
    }

}
