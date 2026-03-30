package cc.wdev.platform.commons.ai.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型类型
 */
@Getter
@AllArgsConstructor
public enum ModelType implements BaseEnum<String> {
    TEXT("TEXT", "文本模型"),
    EMBEDDING("EMBEDDING", "向量模型"),
    IMAGE("IMAGE", "图像模型"),
    AUDIO("AUDIO", "音频模型");

    private final String value;
    private final String description;
}
