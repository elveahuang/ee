package cc.wdev.platform.commons.ai.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

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
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 对话类型
     */
    @Schema(title = "对话类型")
    private String chatType;
    /**
     * 模型
     */
    @Schema(description = "模型名称")
    private String modelCode;
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
