package cc.wdev.platform.commons.ai.model.audio;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import com.openai.client.OpenAIClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.setup.OpenAiSetup;

/**
 * @author elvea
 */
@Slf4j
public class OpenAiAudioTranscriptionSpeechModelFactory
    extends AbstractAudioTranscriptionModelFactory
    implements AudioTranscriptionModelFactory {

    public OpenAiAudioTranscriptionSpeechModelFactory(ProviderConfig config) {
        super(config);
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
        OpenAiAudioTranscriptionOptions.Builder optionsBuilder = OpenAiAudioTranscriptionOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getName());
        } else {
            optionsBuilder.model(OpenAiAudioTranscriptionOptions.DEFAULT_TRANSCRIPTION_MODEL);
        }
        optionsBuilder.responseFormat(OpenAiAudioTranscriptionOptions.DEFAULT_RESPONSE_FORMAT);

        OpenAIClient client = openAiClient(config);
        return OpenAiAudioTranscriptionModel.builder()
            .openAiClient(client)
            .options(optionsBuilder.build())
            .build();
    }

    private OpenAIClient openAiClient(ModelConfig config) {
        return OpenAiSetup.setupSyncClient(config.getBaseUrl(), config.getApiKey(), null,
            null, null,
            null, false, false,
            config.getName(), config.getTimeout(), config.getMaxRetries(), config.getProxy(),
            config.getHeaders());
    }

}
