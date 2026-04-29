package cc.wdev.platform.commons.ai.model.embedding;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import com.openai.client.OpenAIClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.observation.EmbeddingModelObservationConvention;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.setup.OpenAiSetup;
import org.springframework.beans.factory.ObjectProvider;

/**
 * @author elvea
 */
@Slf4j
public class OpenAiEmbeddingModelFactory extends AbstractEmbeddingModelFactory implements EmbeddingModelFactory {

    private final ObjectProvider<ObservationRegistry> observationRegistry;
    private final ObjectProvider<EmbeddingModelObservationConvention> observationConvention;

    public OpenAiEmbeddingModelFactory(ProviderConfig config,
                                       ObjectProvider<ObservationRegistry> observationRegistry,
                                       ObjectProvider<EmbeddingModelObservationConvention> observationConvention
    ) {
        super(config);

        this.observationRegistry = observationRegistry;
        this.observationConvention = observationConvention;
    }

    /**
     * @see EmbeddingModelFactory#getServiceProvider() ()
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
        log.info("Get Model Config for OpenAI EmbeddingModel.");
        SimpleModelConfig.SimpleModelConfigBuilder builder = SimpleModelConfig.builder();
        if (config != null && StringUtils.isNotEmpty(config.getApiKey())) {
            builder.apiKey(config.getApiKey());
        } else {
            builder.apiKey(System.getenv("OPENAI_API_KEY"));
        }
        if (config != null && StringUtils.isNotEmpty(config.getBaseUrl())) {
            builder.baseUrl(config.getBaseUrl());
        }
        if (config != null && StringUtils.isNotEmpty(config.getModels().getEmbedding())) {
            builder.name(config.getModels().getEmbedding());
        }
        return builder.build();
    }

    /**
     * @see EmbeddingModelFactory#getModel(ModelConfig)
     */
    @Override
    public OpenAiEmbeddingModel getModel(ModelConfig config) {
        MetadataMode metadataMode = MetadataMode.EMBED;

        OpenAiEmbeddingOptions.Builder optionsBuilder = OpenAiEmbeddingOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getName());
        } else {
            optionsBuilder.model(OpenAiEmbeddingOptions.DEFAULT_EMBEDDING_MODEL);
        }

        OpenAiEmbeddingModel embeddingModel = new OpenAiEmbeddingModel(this.openAiClient(config),
            metadataMode, optionsBuilder.build(),
            observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP));

        observationConvention.ifAvailable(embeddingModel::setObservationConvention);

        return embeddingModel;
    }

    private OpenAIClient openAiClient(ModelConfig config) {
        return OpenAiSetup.setupSyncClient(config.getBaseUrl(), config.getApiKey(), null,
            null, null,
            null, false, false,
            config.getName(), config.getTimeout(), config.getMaxRetries(), config.getProxy(),
            config.getHeaders());
    }

}
