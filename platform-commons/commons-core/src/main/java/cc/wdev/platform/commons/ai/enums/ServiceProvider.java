package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型服务提供商
 * 这里的服务是指底层的技术实现方案
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ServiceProvider implements BaseEnum<String> {
    DEEPSEEK("deepseek", "深度求索", true),
    DASHSCOPE("dashscope", "阿里云", true),
    OPENAI("openai", "OpenAI", true);

    private final String value;
    private final String description;
    private final boolean enabled;
}
