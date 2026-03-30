package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI消息数据类型枚举
 */
@Getter
@AllArgsConstructor
public enum AiContentType implements BaseEnum<String> {
    TEXT("text", "文本"),
    INTERACTION("interaction", "交互");

    private final String value;
    private final String description;
}
