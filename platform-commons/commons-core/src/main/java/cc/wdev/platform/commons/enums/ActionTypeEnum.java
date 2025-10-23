package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型枚举
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ActionTypeEnum implements BaseBizTypeEnum<String> {
    SAVE("SAVE", "保存"),
    DELETE("DELETE", "删除");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ACTION_TYPE.getValue().toUpperCase();
    }

}
