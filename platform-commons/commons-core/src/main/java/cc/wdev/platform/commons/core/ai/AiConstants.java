package cc.wdev.platform.commons.core.ai;

/**
 * @author elvea
 */
public interface AiConstants {

    /**
     * 默认智能体名称
     */
    String DEFAULT_AGENT_NAME = "智能体";

    /**
     * 系统默认提示词
     */
    String DEFDAULT_PROMPT = """
        你是一个智能体，你需要根据用户的问题，回答用户的问题。
        """;

    /**
     * 最大会话记录数
     */
    int MAX_MEMORY_MESSAGE_COUNT = 36;

    String METADATA_ENTITY_ID = "entityId";

    String METADATA_ENTITY_TYPE = "entityType";

    String METADATA_CHAT_MEMORY_ID = "AiChatMemoryId";

}
