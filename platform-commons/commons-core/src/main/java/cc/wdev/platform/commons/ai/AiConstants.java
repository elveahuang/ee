package cc.wdev.platform.commons.ai;

import java.time.Duration;

/**
 * @author elvea
 */
public interface AiConstants {

    /**
     * 默认超时时间
     */
    Duration DEFAULT_TIMEOUT = Duration.ofSeconds(60);

    /**
     * 默认重试次数
     */
    int DEFAULT_MAX_RETRIES = 3;

    /**
     * 系统默认提示词
     */
    String DEFDAULT_PROMPT = """
        你是一个智能体，你需要根据用户的问题，回答用户的问题。
        """;

    /**
     * 结构化输出提示词
     */
    String STRUCTURED_OUTPUT_PROMPT = """
        请严格遵守以下输出协议：
        1. 回复中的所有 JSON 数据必须被包裹在 [[JSON]] 和 [[END]] 之间。
        2. JSON 标记前后可以有普通说明文字，但标记内部只能包含纯净的 JSON 字符串。
        3. 禁止在标记内使用 Markdown 代码块语法（如 ``` ）。
        4. type: string, content: string
        5. content 字段存放json数据
        6. 获取到的职位信息请返回其json数据
        7. 示例格式：
           这里是数据说明：[[JSON]]{"type":"json","content":"{"name":"data"}"}[[END]]。
        """;

    /**
     * 最大会话记录数
     */
    int MAX_MEMORY_MESSAGE_COUNT = 36;

    String METADATA_TENANT_ID = "tenantId";

    String METADATA_USER_ID = "userId";

    String METADATA_CHAT_MEMORY_ID = "chatMemoryId";

    String CHAT_TYPE = "chatType";

    String IGNORE_API_KEY = "*************";

}
