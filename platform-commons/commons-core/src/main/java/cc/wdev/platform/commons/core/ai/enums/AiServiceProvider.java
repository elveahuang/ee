package cc.wdev.platform.commons.core.ai.enums;

import java.io.Serializable;

/**
 * 智能体服务
 * SPRING   - 基于 Spring AI 的智能体服务
 * LC       - 基于 LangChain4j 的智能体服务
 *
 * @author elvea
 */
public enum AiServiceProvider implements Serializable {
    SPRING, LC
}
