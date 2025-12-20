package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatService;
import cc.wdev.platform.commons.core.ai.enums.AiServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

/**
 * @author elvea
 */
@Slf4j
public class AiFactoryImpl implements AiFactory {

    private final AiServiceProvider provider;

    private final ChatService chatService;

    private final MessageWindowChatMemory messageWindowChatMemory;

    public AiFactoryImpl(AiServiceProvider provider,
                         ChatService chatService,
                         MessageWindowChatMemory messageWindowChatMemory) {
        this.provider = provider;
        this.chatService = chatService;
        this.messageWindowChatMemory = messageWindowChatMemory;
    }

    /**
     * @see AiFactory#getChatMemory()
     */
    @Override
    public MessageWindowChatMemory getChatMemory() {
        return messageWindowChatMemory;
    }

    /**
     * @see AiFactory#getChatService()
     */
    @Override
    public ChatService getChatService() {
        if (AiServiceProvider.SPRING.equals(this.provider)) {
            return chatService;
        }
        return chatService;
    }

}
