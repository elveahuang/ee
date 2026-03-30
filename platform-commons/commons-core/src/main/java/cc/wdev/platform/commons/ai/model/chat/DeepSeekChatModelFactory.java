package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.config.SimpleModelConfig;
import cc.wdev.platform.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;

/**
 * @author elvea
 */
@Slf4j
public class DeepSeekChatModelFactory extends AbstractChatModelFactory implements ChatModelFactory {

    public DeepSeekChatModelFactory(ChatMemory chatMemory, ProviderConfig config) {
        super(chatMemory, config);
    }

    /**
     * @see ChatModelFactory#getModelProvider()
     */
    @Override
    public ModelProvider getModelProvider() {
        return ModelProvider.DEEPSEEK;
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

        DeepSeekChatOptions.Builder optionsBuilder = DeepSeekChatOptions.builder();
        if (StringUtils.isNotEmpty(config.getName())) {
            optionsBuilder.model(config.getName());
        } else {
            optionsBuilder.model(DeepSeekApi.ChatModel.DEEPSEEK_CHAT.getValue());
        }

        // ::todo
//        // 开启思考模式
//        if (null != config.getReturnThinking() && config.getReturnThinking()) {
//
//        }
//        // 联网搜索
//        if (null != config.getEnableWebSearch() && config.getEnableWebSearch()) {
//
//        }

        return DeepSeekChatModel.builder().deepSeekApi(apiBuilder.build()).defaultOptions(optionsBuilder.build()).build();
    }

}
