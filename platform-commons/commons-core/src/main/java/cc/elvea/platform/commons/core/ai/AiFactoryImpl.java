package cc.elvea.platform.commons.core.ai;

import cc.elvea.platform.commons.core.ai.chat.ChatCompletionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

/**
 * @author elvea
 */
@Slf4j
public class AiFactoryImpl implements AiFactory {

    private final AiProvider provider;

    private final ChatCompletionService chatCompletionService;

    private final MessageWindowChatMemory messageWindowChatMemory;

    public AiFactoryImpl(AiProvider provider,
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
        if (AiProvider.SPRING.equals(this.provider)) {
            return chatCompletionService;
        }
        return chatCompletionService;
    }

}
