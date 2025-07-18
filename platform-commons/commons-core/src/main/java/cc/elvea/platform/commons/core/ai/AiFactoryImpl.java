package cc.elvea.platform.commons.core.ai;

import cc.elvea.platform.commons.core.ai.chat.ChatCompletionService;
import cc.elvea.platform.commons.core.ai.enums.AiServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

/**
 * @author elvea
 */
@Slf4j
public class AiFactoryImpl implements AiFactory {

    private final AiServiceProvider provider;

    private final ChatCompletionService chatCompletionService;

    private final MessageWindowChatMemory messageWindowChatMemory;

    public AiFactoryImpl(AiServiceProvider provider,
                         ChatCompletionService chatCompletionService,
                         MessageWindowChatMemory messageWindowChatMemory) {
        this.provider = provider;
        this.chatCompletionService = chatCompletionService;
        this.messageWindowChatMemory = messageWindowChatMemory;
    }

    @Override
    public MessageWindowChatMemory getMessageWindowChatMemory() {
        return messageWindowChatMemory;
    }

    @Override
    public ChatCompletionService getChatCompletionService() {
        if (AiServiceProvider.SPRING.equals(this.provider)) {
            return chatCompletionService;
        }
        return chatCompletionService;
    }

}
