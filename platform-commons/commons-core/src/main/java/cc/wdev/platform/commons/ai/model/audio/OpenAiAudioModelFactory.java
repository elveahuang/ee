package cc.wdev.platform.commons.ai.model.audio;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.api.OpenAiAudioApi;

/**
 * @author elvea
 */
@Slf4j
@RequiredArgsConstructor
public class OpenAiAudioModelFactory extends AbstractAudioModelFactory implements AudioModelFactory {

    private final ProviderConfig config;

    /**
     * @see ModelFactory#getModelProvider()
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
        log.info("Get Model Config for OpenAI AudioModel.");
        SimpleModelConfig.SimpleModelConfigBuilder builder = SimpleModelConfig.builder();
        if (config != null && StringUtils.isNotEmpty(config.getApiKey())) {
            builder.apiKey(config.getApiKey());
        } else {
            builder.apiKey(System.getenv("OPENAI_API_KEY"));
        }
        if (config != null && StringUtils.isNotEmpty(config.getBaseUrl())) {
            builder.baseUrl(config.getBaseUrl());
        }
        if (config != null && StringUtils.isNotEmpty(config.getModels().getTranscription())) {
            builder.name(config.getModels().getTranscription());
        }
        return builder.build();
    }

    /**
     * @see ModelFactory#getModel(ModelConfig)
     */
    @Override
    public OpenAiAudioTranscriptionModel getModel(ModelConfig config) {
        OpenAiAudioApi openAiAudioApi = OpenAiAudioApi.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .build();
        return new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

}
