package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.observation.ChatModelObservationConvention;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author elvea
 */
@Slf4j
public class DeepSeekChatModelFactory extends AbstractChatModelFactory implements ChatModelFactory {

    private final ToolCallingManager toolCallingManager;
    private final ObjectProvider<RestClient.Builder> restClientBuilderProvider;
    private final ObjectProvider<WebClient.Builder> webClientBuilderProvider;
    private final ObjectProvider<RetryTemplate> retryTemplate;
    private final ObjectProvider<ResponseErrorHandler> responseErrorHandler;
    private final ObjectProvider<ObservationRegistry> observationRegistry;
    private final ObjectProvider<ChatModelObservationConvention> observationConvention;

    public DeepSeekChatModelFactory(ChatMemory chatMemory,
                                    ProviderConfig config,
                                    ToolCallingManager toolCallingManager,
                                    ObjectProvider<RestClient.Builder> restClientBuilderProvider,
                                    ObjectProvider<WebClient.Builder> webClientBuilderProvider,
                                    ObjectProvider<RetryTemplate> retryTemplate,
                                    ObjectProvider<ResponseErrorHandler> responseErrorHandler,
                                    ObjectProvider<ObservationRegistry> observationRegistry,
                                    ObjectProvider<ChatModelObservationConvention> observationConvention
    ) {
        super(chatMemory, config);

        this.toolCallingManager = toolCallingManager;
        this.restClientBuilderProvider = restClientBuilderProvider;
        this.webClientBuilderProvider = webClientBuilderProvider;
        this.retryTemplate = retryTemplate;
        this.responseErrorHandler = responseErrorHandler;
        this.observationRegistry = observationRegistry;
        this.observationConvention = observationConvention;
    }

    /**
     * @see ModelFactory#getServiceProvider()
     */
    @Override
    public ServiceProvider getServiceProvider() {
        return ServiceProvider.DEEPSEEK;
    }

    /**
     * @see ChatModelFactory#getModel(ModelConfig)
     */
    @Override
    public ModelConfig getModelConfig() {
        log.info("Get Model Config for DeepSeek ChatModel.");
        SimpleModelConfig.SimpleModelConfigBuilder builder = SimpleModelConfig.builder();
        if (config != null && StringUtils.isNotEmpty(config.getApiKey())) {
            builder.apiKey(config.getApiKey());
        } else {
            builder.apiKey(System.getenv("DEEPSEEK_API_KEY"));
        }
        if (config != null && StringUtils.isNotEmpty(config.getBaseUrl())) {
            log.info("Get Model Config for DeepSeek ChatModel with BaseUrl {}.", config.getBaseUrl());
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
    public DeepSeekChatModel getModel(ModelConfig config) {
        DeepSeekApi.Builder apiBuilder = DeepSeekApi.builder().apiKey(config.getApiKey());
        if (StringUtils.isNotEmpty(config.getBaseUrl())) {
            apiBuilder.baseUrl(config.getBaseUrl());
        }
        apiBuilder.restClientBuilder(this.restClientBuilderProvider.getIfAvailable(RestClient::builder));
        apiBuilder.webClientBuilder(this.webClientBuilderProvider.getIfAvailable(WebClient::builder));
        apiBuilder.responseErrorHandler(this.responseErrorHandler.getIfAvailable(() -> RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER));

        DeepSeekChatOptions.Builder optionsBuilder = DeepSeekChatOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getName());
        } else {
            optionsBuilder.model(DeepSeekApi.ChatModel.DEEPSEEK_CHAT.getValue());
        }
        optionsBuilder.internalToolExecutionEnabled(config.getInternalToolExecutionEnabled());

        DeepSeekChatModel chatModel = DeepSeekChatModel.builder()
            .deepSeekApi(apiBuilder.build())
            .defaultOptions(optionsBuilder.build())
            .toolCallingManager(toolCallingManager)
            .retryTemplate(retryTemplate.getIfUnique(() -> RetryUtils.DEFAULT_RETRY_TEMPLATE))
            .observationRegistry(observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP))
            .build();

        observationConvention.ifAvailable(chatModel::setObservationConvention);

        return chatModel;
    }

}
