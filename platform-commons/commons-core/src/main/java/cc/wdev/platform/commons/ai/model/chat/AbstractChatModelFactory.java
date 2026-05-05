package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.advisor.CustomLoggingAdvisor;
import cc.wdev.platform.commons.ai.advisor.UserContextChatMemoryAdvisor;
import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelType;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

/**
 * @author elvea
 */
@Slf4j
public abstract class AbstractChatModelFactory implements ChatModelFactory {

    protected final ChatMemory chatMemory;

    protected final ProviderConfig config;

    public AbstractChatModelFactory(ChatMemory chatMemory, ProviderConfig config) {
        this.chatMemory = chatMemory;
        this.config = config;
    }

    /**
     * @see ChatModelFactory#getModelType()
     */
    @Override
    public ModelType getModelType() {
        return ModelType.TEXT;
    }

    /**
     * @see ChatModelFactory#getChatClient(ModelConfig)
     */
    @Override
    public ChatClient getChatClient(ModelConfig config) {
        ChatClient.Builder chatClientBuilder = ChatClient.builder(this.getModel(config))
            .defaultAdvisors(
                new UserContextChatMemoryAdvisor(),
                MessageChatMemoryAdvisor.builder(chatMemory).scheduler(MessageChatMemoryAdvisor.DEFAULT_SCHEDULER).build(),
                new CustomLoggingAdvisor()
            );
        log.info("Create ChatClient: {}", config.getName());
        return chatClientBuilder.build();
    }

}
