package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatService;
import cc.wdev.platform.commons.core.ai.enums.AiServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;

/**
 * @author elvea
 */
@Slf4j
public class AiFactoryImpl implements AiFactory {

    private final AiServiceProvider provider;

    private final ChatService chatService;

    private final ChatMemory chatMemory;

    public AiFactoryImpl(AiServiceProvider provider,
                         ChatService chatService,
                         ChatMemory chatMemory) {
        this.provider = provider;
        this.chatService = chatService;
        this.chatMemory = chatMemory;
    }

    /**
     * @see AiFactory#getChatMemory()
     */
    @Override
    public ChatMemory getChatMemory() {
        return chatMemory;
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
