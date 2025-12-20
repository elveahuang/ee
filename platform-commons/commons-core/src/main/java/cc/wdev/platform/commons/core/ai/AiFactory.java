package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatService;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

/**
 * @author elvea
 */
public interface AiFactory {

    /**
     * 获取智能体存储服务
     */
    MessageWindowChatMemory getChatMemory();

    /**
     * 获取智能体服务
     */
    ChatService getChatService();

}
