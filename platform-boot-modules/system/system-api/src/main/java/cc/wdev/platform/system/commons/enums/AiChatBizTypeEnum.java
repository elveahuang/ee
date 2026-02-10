package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI聊天类型
 * ASSISTANT - 助手对话
 * CUSTOMER_SERVICE - 客服对话
 * KNOWLEDGE_BASE - 知识库问答
 * CODE_REVIEW - 代码审查
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AiChatBizTypeEnum implements BaseEnum<String> {
    ASSISTANT("ASSISTANT", "助手对话");

    private final String value;
    private final String description;
}
