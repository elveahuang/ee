package cc.wdev.platform.commons.ai;

/**
 * @author elvea
 */
public interface AiConstants {

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

    String METADATA_USER_ID = "userId";

    String METADATA_CHAT_MEMORY_ID = "chatMemoryId";

    String CHAT_TYPE = "chatType";

}
