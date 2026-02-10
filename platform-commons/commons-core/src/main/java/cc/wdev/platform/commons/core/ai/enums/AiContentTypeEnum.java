package cc.wdev.platform.commons.core.ai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI消息数据类型枚举
 */
@AllArgsConstructor
@Getter
public enum AiContentTypeEnum {
    TEXT("text", "文本", ""),
    INTERACTION("interaction", "交互", "");

    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String desc;
    /**
     * 多语言描述
     */
    private final String label;
}
