package cc.wdev.platform.commons.ai;

import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.enums.BaseEnum;
import cc.wdev.platform.commons.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author elvea
 */
@Slf4j
public class AiManagerImpl implements AiManager {

    private final List<ModelFactory<?>> factories = new ArrayList<>();

    private final ChatMemory chatMemory;

    public AiManagerImpl(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
    }

    public void addFactory(List<ModelFactory<?>> factories) {
        this.factories.addAll(factories);
    }

    /**
     * @see AiManager#getChatMemory()
     */
    @Override
    public ChatMemory getChatMemory() {
        return chatMemory;
    }

    /**
     * @see AiManager#getChatModelFactory()
     */
    @Override
    public ChatModelFactory getChatModelFactory() {
        return factories.stream()
            .map(factory -> {
                if (factory instanceof ChatModelFactory chatModelFactory) {
                    return chatModelFactory;
                }
                return null;
            })
            .filter(Objects::nonNull)
            .filter(factory -> {
                ModelProvider provider = BaseEnum.getEnumByValue(factory.getProviderId(), ModelProvider.class);
                return provider != null && provider.isEnabled();
            })
            .findFirst()
            .orElseThrow(() -> new SystemException("Unavailable provider."));
    }

    /**
     * @see AiManager#getChatModelFactory(ModelConfig)
     */
    @Override
    public ChatModelFactory getChatModelFactory(ModelConfig config) {
        if (config.getProviderCode() == null) {
            throw new IllegalArgumentException("Model provider code cannot be null");
        }
        if (config.getName() == null) {
            throw new IllegalArgumentException("Model name cannot be null");
        }
        for (ModelFactory<?> factory : factories) {
            if (factory instanceof ChatModelFactory chatModelFactory && chatModelFactory.supports(config)) {
                return chatModelFactory;
            }
        }
        return getChatModelFactory();
    }

    /**
     * @see AiManager#getChatModel()
     */
    @Override
    public ChatModel getChatModel() {
        return this.getChatModelFactory().getChatModel();
    }

    /**
     * @see AiManager#getChatModel(ModelConfig)
     */
    @Override
    public ChatModel getChatModel(ModelConfig config) {
        return this.getChatModelFactory(config).getChatModel(config);
    }

    /**
     * @see AiManager#getChatClient()
     */
    @Override
    public ChatClient getChatClient() {
        return this.getChatModelFactory().getChatClient();
    }

    /**
     * @see AiManager#getChatClient(ModelConfig)
     */
    @Override
    public ChatClient getChatClient(ModelConfig config) {
        return this.getChatModelFactory(config).getChatClient(config);
    }

    /**
     * @see AiManager#getAvailableProviders()
     */
    public List<ModelProvider> getAvailableProviders() {
        return this.factories.stream()
            .filter(factory -> {
                ModelProvider provider = BaseEnum.getEnumByValue(factory.getProviderId(), ModelProvider.class);
                return provider != null && provider.isEnabled();
            })
            .map(factory -> BaseEnum.getEnumByValue(factory.getProviderId(), ModelProvider.class))
            .collect(Collectors.toList());
    }

}
