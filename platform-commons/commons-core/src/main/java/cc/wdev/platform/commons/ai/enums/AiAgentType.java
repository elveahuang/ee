package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型类型
 */
@Getter
@AllArgsConstructor
public enum AiAgentType implements BaseEnum<String> {
    MODEL_TYPE("model_type", "模型类型"),
    TOOL_TYPE("tool_type", "工具类型"),
    MCP_SERVER_TYPE("mcp_server_type", "MCP服务类型"),
    KNOWLEDGE_BASE_TYPE("knowledge_type", "知识库类型");

    private final String value;
    private final String description;
}
