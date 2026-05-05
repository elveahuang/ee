package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import com.openai.client.OpenAIClient;
import com.openai.client.OpenAIClientAsync;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.observation.ChatModelObservationConvention;
import org.springframework.ai.model.tool.DefaultToolExecutionEligibilityPredicate;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionEligibilityPredicate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.setup.OpenAiSetup;
import org.springframework.beans.factory.ObjectProvider;

/**
 * @author elvea
 */
@Slf4j
public class OpenAiChatModelFactory extends AbstractChatModelFactory implements ChatModelFactory {

    private final ToolCallingManager toolCallingManager;
    private final ObjectProvider<ObservationRegistry> observationRegistry;
    private final ObjectProvider<ChatModelObservationConvention> observationConvention;
    private final ObjectProvider<ToolExecutionEligibilityPredicate> openAiToolExecutionEligibilityPredicate;

    public OpenAiChatModelFactory(ChatMemory chatMemory,
                                  ProviderConfig config,
                                  ToolCallingManager toolCallingManager,
                                  ObjectProvider<ObservationRegistry> observationRegistry,
                                  ObjectProvider<ChatModelObservationConvention> observationConvention,
                                  ObjectProvider<ToolExecutionEligibilityPredicate> openAiToolExecutionEligibilityPredicate
    ) {
        super(chatMemory, config);

        this.toolCallingManager = toolCallingManager;
        this.observationRegistry = observationRegistry;
        this.observationConvention = observationConvention;
        this.openAiToolExecutionEligibilityPredicate = openAiToolExecutionEligibilityPredicate;
    }

    /**
     * @see ModelFactory#getServiceProvider()
     */
    @Override
    public ServiceProvider getServiceProvider() {
        return ServiceProvider.OPENAI;
    }

    /**
     * @see ChatModelFactory#getModel(ModelConfig)
     */
    @Override
    public ModelConfig getModelConfig() {
        log.info("Get Model Config for OpenAI ChatModel.");
        SimpleModelConfig.SimpleModelConfigBuilder builder = SimpleModelConfig.builder();
        if (config != null && StringUtils.isNotEmpty(config.getApiKey())) {
            builder.apiKey(config.getApiKey());
        } else {
            builder.apiKey(System.getenv("OPENAI_API_KEY"));
        }
        if (config != null && StringUtils.isNotEmpty(config.getBaseUrl())) {
            log.info("Get Model Config for OpenAI ChatModel with BaseUrl {}.", config.getBaseUrl());
            builder.baseUrl(config.getBaseUrl());
        }
        if (config != null && StringUtils.isNotEmpty(config.getModels().getChat())) {
            builder.name(config.getModels().getChat());
        }
        return builder.build();
    }

    /**
     * @see ChatModelFactory#getModel(ModelConfig)
     */
    @Override
    public OpenAiChatModel getModel(ModelConfig config) {
        OpenAiChatOptions.Builder optionsBuilder = OpenAiChatOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getName());
        }
        optionsBuilder.internalToolExecutionEnabled(config.getInternalToolExecutionEnabled());

        OpenAIClient openAIClient = this.openAiClient(config);
        OpenAIClientAsync openAIClientAsync = this.openAiClientAsync(config);

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
            .openAiClient(openAIClient)
            .openAiClientAsync(openAIClientAsync)
            .options(optionsBuilder.build())
            .toolCallingManager(this.toolCallingManager)
            .observationRegistry(this.observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP))
            .toolExecutionEligibilityPredicate(this.openAiToolExecutionEligibilityPredicate.getIfUnique(DefaultToolExecutionEligibilityPredicate::new))
            .build();

        observationConvention.ifAvailable(chatModel::setObservationConvention);

        return chatModel;
    }

    private OpenAIClient openAiClient(ModelConfig config) {
        return OpenAiSetup.setupSyncClient(config.getBaseUrl(), config.getApiKey(), null,
            null, null,
            null, false, false,
            config.getName(), config.getTimeout(), config.getMaxRetries(), config.getProxy(),
            config.getHeaders());
    }

    private OpenAIClientAsync openAiClientAsync(ModelConfig config) {
        return OpenAiSetup.setupAsyncClient(config.getBaseUrl(), config.getApiKey(), null,
            null, null,
            null, false, false,
            config.getName(), config.getTimeout(), config.getMaxRetries(), config.getProxy(),
            config.getHeaders());
    }

}
