package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Mcp Server Type
 */
@Getter
@AllArgsConstructor
public enum McpServerProtocol implements BaseEnum<String> {
    SSE("SSE", "SSE"),
    STREAMABLE_HTTP("STREAMABLE_HTTP", "Streamable-HTTP");

    private final String value;
    private final String description;
}
