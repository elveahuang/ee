package cc.wdev.platform.commons.ai.model.embedding;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;

/**
 * @author elvea
 */
@Slf4j
@RequiredArgsConstructor
public class OpenAiEmbeddingModelFactory extends AbstractEmbeddingModelFactory implements EmbeddingModelFactory {

    private final ProviderConfig config;

    /**
     * @see EmbeddingModelFactory#getModelProvider()
     */
    @Override
    public ModelProvider getModelProvider() {
        return ModelProvider.OPENAI;
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
        OpenAiApi openAiApi = OpenAiApi.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .build();

        return new OpenAiEmbeddingModel(
            openAiApi,
            MetadataMode.EMBED,
            OpenAiEmbeddingOptions.builder()
                .model("text-embedding-ada-002")
                .user("user-6")
                .build(),
            RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }

}
