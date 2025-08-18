package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatCompletionService;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

/**
 * @author elvea
 */
public interface AiFactory {

    MessageWindowChatMemory getMessageWindowChatMemory();

    ChatCompletionService getChatCompletionService();

}
