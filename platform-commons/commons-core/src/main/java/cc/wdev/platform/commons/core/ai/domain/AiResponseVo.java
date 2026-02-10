package cc.wdev.platform.commons.core.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiResponseVo<T> implements Serializable {
    /**
     * 内容类型
     * 1. 文本 text
     * 2. 互动 interaction
     */
    private String contentType;
    /**
     * 交互类型
     */
    private String interactionType;
    private T data;
    private String message;
    private String more;
}
