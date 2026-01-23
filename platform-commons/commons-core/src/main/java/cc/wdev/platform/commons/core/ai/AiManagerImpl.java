package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.chat.ChatService;
import cc.wdev.platform.commons.core.ai.enums.AiServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;

/**
 * @author elvea
 */
@Slf4j
public class AiManagerImpl implements AiManager {

    private final AiServiceProvider provider;

    private final ChatService chatService;

    private final ChatModel chatModel;

    private final ChatMemory chatMemory;

    public AiManagerImpl(AiServiceProvider provider,
                         ChatModel chatModel,
                         ChatService chatService,
                         ChatMemory chatMemory) {
        this.provider = provider;
        this.chatService = chatService;
        this.chatModel = chatModel;
        this.chatMemory = chatMemory;
    }

    /**
     * @see AiManager#getChatMemory()
     */
    @Override
    public ChatMemory getChatMemory() {
        return chatMemory;
    }

    /**
     * @see AiManager#getChatModel()
     */
    @Override
    public ChatModel getChatModel() {
        return chatModel;
    }

    /**
     * @see AiManager#getChatService()
     */
    @Override
    public ChatService getChatService() {
        if (AiServiceProvider.SPRING.equals(this.provider)) {
            return chatService;
        }
        return chatService;
    }

}
