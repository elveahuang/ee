package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

/**
 * @author elvea
 */
@Slf4j
public class OpenAiChatModelFactory extends AbstractChatModelFactory implements ChatModelFactory {

    public OpenAiChatModelFactory(ChatMemory chatMemory, ProviderConfig config) {
        super(chatMemory, config);
    }

    /**
     * @see ChatModelFactory#getModelProvider()
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
        log.info("Get Model Config for OpenAI ChatModel.");
        SimpleModelConfig.SimpleModelConfigBuilder builder = SimpleModelConfig.builder();
        if (config != null && StringUtils.isNotEmpty(config.getApiKey())) {
            builder.apiKey(config.getApiKey());
        } else {
            builder.apiKey(System.getenv("OPENAI_API_KEY"));
        }
        if (config != null && StringUtils.isNotEmpty(config.getBaseUrl())) {
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
        OpenAiApi.Builder apiBuilder = OpenAiApi.builder().apiKey(config.getApiKey());
        if (StringUtils.isNotEmpty(config.getBaseUrl())) {
            apiBuilder.baseUrl(config.getBaseUrl());
        }

        OpenAiChatOptions.Builder optionsBuilder = OpenAiChatOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getModelType());
        } else {
            optionsBuilder.model(OpenAiApi.ChatModel.GPT_5.getValue());
        }

        // todo
//        // 开启思考模式
//        if (null != config.getReturnThinking() && config.getReturnThinking()) {
//
//        }
        // 联网搜索
        if (null != config.getEnableWebSearch() && config.getEnableWebSearch()) {
            OpenAiApi.ChatCompletionRequest.WebSearchOptions.UserLocation.Approximate approximate =
                new OpenAiApi.ChatCompletionRequest.WebSearchOptions.UserLocation.Approximate("guangzhou", "China", "Guangdong", "Asia/Shanghai");
            OpenAiApi.ChatCompletionRequest.WebSearchOptions.UserLocation userLocation =
                new OpenAiApi.ChatCompletionRequest.WebSearchOptions.UserLocation("approximate", approximate);
            optionsBuilder.webSearchOptions(
                new OpenAiApi.ChatCompletionRequest.WebSearchOptions(OpenAiApi.ChatCompletionRequest.WebSearchOptions.SearchContextSize.MEDIUM, userLocation)
            );
        }

        return OpenAiChatModel.builder().openAiApi(apiBuilder.build()).defaultOptions(optionsBuilder.build()).build();
    }

}
