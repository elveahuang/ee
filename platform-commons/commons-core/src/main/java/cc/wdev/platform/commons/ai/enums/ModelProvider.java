package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 模型提供商
 */
@Getter
@AllArgsConstructor
public enum ModelProvider implements BaseEnum<String> {
    DEEPSEEK("deepseek", "深度求索", true,
        new Model[]{
            new Model("deepseek-chat", true, false, ModelType.TEXT),
            new Model("deepseek-reasoner", true, false, ModelType.TEXT),
            new Model("deepseek-v4-flash", true, false, ModelType.TEXT),
            new Model("deepseek-v4-pro", true, false, ModelType.TEXT)
        },
        new ModelType[]{ModelType.TEXT}
    ),

    ALIYUN("aliyun", "阿里云", true,
        new Model[]{
            new Model("deepseek-chat", true, false, ModelType.TEXT),
            new Model("deepseek-reasoner", true, false, ModelType.TEXT),
            new Model("deepseek-v4-flash", true, false, ModelType.TEXT),
            new Model("deepseek-v4-pro", true, false, ModelType.TEXT)
        },
        new ModelType[]{ModelType.TEXT, ModelType.AUDIO, ModelType.EMBEDDING}
    ),

    DASHSCOPE("dashscope", "阿里云", true,
        new Model[]{
            new Model("deepseek-chat", true, false, ModelType.TEXT),
            new Model("deepseek-reasoner", true, false, ModelType.TEXT),
        },
        new ModelType[]{ModelType.TEXT, ModelType.EMBEDDING}
    ),

    OPENAI("openai", "OpenAI", true,
        new Model[]{
            new Model("deepseek-chat", true, false, ModelType.TEXT),
            new Model("deepseek-reasoner", true, false, ModelType.TEXT),
        },
        new ModelType[]{ModelType.TEXT, ModelType.EMBEDDING}
    );

    private final String value;
    private final String description;
    private final boolean enabled;
    private final Model[] models;
    private final ModelType[] types;

    public boolean supportsType(ModelType type) {
        return type != null && Arrays.asList(types).contains(type);
    }

    public boolean supportsModel(String modelName) {
        return getModel(modelName).isPresent();
    }

    private Optional<Model> getModel(String modelName) {
        if (modelName == null) return Optional.empty();
        return Arrays.stream(models)
            .filter(cap -> modelName.equals(cap.modelName()) || modelName.startsWith(cap.modelName() + "-"))
            .findFirst();
    }

    /**
     * 模型定义
     *
     * @param modelName           模型名称
     * @param deepThinkingEnabled 是否支持深度思考
     * @param webSearchEnabled    是否支持网络搜索
     * @param type                模型类型
     */
    public record Model(String modelName, boolean deepThinkingEnabled, boolean webSearchEnabled, ModelType type) {
    }
}
