package cc.wdev.platform.commons.core.ai.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "简单对话请求")
public class SimpleChatRequest implements Serializable {
    /**
     * 会话ID
     */
    @Schema(description = "会话ID")
    private String conversationId;

    @Schema(description = "实体ID")
    private Long entityId;

    @Schema(description = "实体类型")
    private String entityType;
    /**
     * 用户提示词
     */
    @Schema(description = "用户提示词")
    private String prompt;
    /**
     * 系统提示词
     */
    @Schema(description = "系统提示")
    private String systemPrompt;
    /**
     * 指定执行工具
     */
    @Schema(description = "指定执行工具")
    private String tool;
    /**
     * 输出格式
     */
    @Schema(description = "输出格式")
    private String format;
    /**
     * 是否强制刷新
     */
    @Schema(description = "是否强制刷新")
    private boolean force;
    /**
     * 是否由框架自行控制工具执行
     */
    @Builder.Default
    @Schema(description = "是否由框架自行控制工具执行")
    private boolean internalToolExecutionEnabled = true;
}
