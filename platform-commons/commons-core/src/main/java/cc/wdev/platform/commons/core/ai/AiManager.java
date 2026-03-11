package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatService;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;

/**
 * @author elvea
 */
public interface AiManager {

    /**
     * 获取智能体存储服务
     */
    ChatMemory getChatMemory();

    /**
     * 获取智能体服务
     */
    ChatModel getChatModel();

    /**
     * 获取智能体服务
     */
    ChatService getChatService();

}
