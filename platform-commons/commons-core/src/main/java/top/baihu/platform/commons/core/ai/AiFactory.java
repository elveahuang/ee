package top.baihu.platform.commons.core.ai;

import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import top.baihu.platform.commons.core.ai.chat.ChatCompletionService;

/**
 * @author elvea
 */
public interface AiFactory {

    MessageWindowChatMemory getMessageWindowChatMemory();

    ChatCompletionService getChatCompletionService();

}
