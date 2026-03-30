package cc.wdev.platform.commons.autoconfigure.ai;

import cc.wdev.platform.commons.ai.AiManager;
import cc.wdev.platform.commons.ai.AiManagerImpl;
import cc.wdev.platform.commons.ai.aliyun.AiAliyunManager;
import cc.wdev.platform.commons.ai.aliyun.AiAliyunManagerImpl;
import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.config.ProviderModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import cc.wdev.platform.commons.ai.model.audio.OpenAiAudioModelFactory;
import cc.wdev.platform.commons.ai.model.chat.DeepSeekChatModelFactory;
import cc.wdev.platform.commons.ai.model.chat.OpenAiChatModelFactory;
import cc.wdev.platform.commons.ai.model.embedding.OpenAiEmbeddingModelFactory;
import cc.wdev.platform.commons.ai.tencent.AiTencentManager;
import cc.wdev.platform.commons.ai.tencent.AiTencentManagerImpl;
import cc.wdev.platform.commons.ai.tools.CommonTools;
import cc.wdev.platform.commons.autoconfigure.ai.properties.AiProperties;
import com.alibaba.dashscope.common.DashScopeResult;
import com.tencentcloudapi.hunyuan.v20230901.HunyuanClient;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.List;
import java.util.Set;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.ai.vectorstore.VectorStore")
@EnableConfigurationProperties({AiProperties.class})
@ImportRuntimeHints(AiAutoConfiguration.AiRuntimeHints.class)
public class AiAutoConfiguration {

    private final AiProperties properties;

    public AiAutoConfiguration(AiProperties properties) {
        log.info("AI is enabled");
        log.info("AI Provider deepseek - {}", properties.getProviders().getDeepseek().isEnabled());
        log.info("AI Provider openai - {}", properties.getProviders().getOpenai().isEnabled());
        log.info("AI Provider aliyun - {}", properties.getProviders().getAliyun().isEnabled());
        log.info("AI Provider tencent - {}", properties.getProviders().getTencent().isEnabled());

        this.properties = properties;
    }

    // ------------------------------------------------------------------------------
    // Base
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatMemory chatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(100)
            .build();
    }

    // ------------------------------------------------------------------------------
    // Chat Model
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.deepseek.enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(DeepSeekChatModel.class)
    public DeepSeekChatModelFactory deepSeekChatModelFactory(ChatMemory chatMemory) {
        return new DeepSeekChatModelFactory(chatMemory, getDeepSeekConfig());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.openai.enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(OpenAiChatModel.class)
    public OpenAiChatModelFactory openAiChatModelFactory(ChatMemory chatMemory) {
        return new OpenAiChatModelFactory(chatMemory, getOpenAiConfig());
    }

    // ------------------------------------------------------------------------------
    // Image Model
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.openai.enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(OpenAiAudioTranscriptionModel.class)
    public OpenAiAudioModelFactory openAiAudioModelFactory() {
        return new OpenAiAudioModelFactory(getOpenAiConfig());
    }

    // ------------------------------------------------------------------------------
    // Embeddings Model
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.openai.enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(OpenAiEmbeddingModel.class)
    public OpenAiEmbeddingModelFactory openAiEmbeddingModelFactory() {
        return new OpenAiEmbeddingModelFactory(getOpenAiConfig());
    }

    // ------------------------------------------------------------------------------
    // Model Manager
    // ------------------------------------------------------------------------------


    @Bean
    @ConditionalOnMissingBean
    public AiManager aiManager(List<ModelFactory<?>> factories, ChatMemory chatMemory) {
        AiManagerImpl aiManager = new AiManagerImpl(chatMemory);
        aiManager.addFactory(factories);
        return aiManager;
    }

    // ------------------------------------------------------------------------------
    // 腾讯云大模型
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.tencent.enabled", havingValue = "true")
    @ConditionalOnClass(HunyuanClient.class)
    public AiTencentManager aiTencentFactory() {
        return new AiTencentManagerImpl(getTencentConfig());
    }

    // ------------------------------------------------------------------------------
    // 阿里云大模型
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "providers.aliyun.enabled", havingValue = "true")
    @ConditionalOnClass(DashScopeResult.class)
    public AiAliyunManager aiAliyunFactory() {
        return new AiAliyunManagerImpl(getAliyunConfig());
    }

    // ------------------------------------------------------------------------------
    // 辅助方法
    // ------------------------------------------------------------------------------

    private ProviderConfig getDeepSeekConfig() {
        ProviderModelConfig models = ProviderModelConfig.builder()
            .chat(properties.getProviders().getDeepseek().getModels().getChat())
            .build();
        return ProviderConfig.builder()
            .apiKey(properties.getProviders().getDeepseek().getApiKey())
            .baseUrl(properties.getProviders().getDeepseek().getBaseUrl())
            .models(models)
            .build();
    }

    private ProviderConfig getOpenAiConfig() {
        ProviderModelConfig models = ProviderModelConfig.builder()
            .chat(properties.getProviders().getOpenai().getModels().getChat())
            .embedding(properties.getProviders().getOpenai().getModels().getChat())
            .transcription(properties.getProviders().getOpenai().getModels().getTranscription())
            .image(properties.getProviders().getOpenai().getModels().getImage())
            .build();
        return ProviderConfig.builder()
            .apiKey(properties.getProviders().getOpenai().getApiKey())
            .baseUrl(properties.getProviders().getOpenai().getBaseUrl())
            .models(models)
            .build();
    }

    private ProviderConfig getAliyunConfig() {
        ProviderModelConfig models = ProviderModelConfig.builder()
            .chat(properties.getProviders().getAliyun().getModels().getChat())
            .embedding(properties.getProviders().getAliyun().getModels().getChat())
            .transcription(properties.getProviders().getAliyun().getModels().getTranscription())
            .image(properties.getProviders().getAliyun().getModels().getImage())
            .build();

        return ProviderConfig.builder()
            .apiKey(properties.getProviders().getAliyun().getApiKey())
            .baseUrl(properties.getProviders().getAliyun().getBaseUrl())
            .models(models)
            .build();
    }

    private ProviderConfig getTencentConfig() {
        ProviderModelConfig models = ProviderModelConfig.builder()
            .chat(properties.getProviders().getTencent().getModels().getChat())
            .embedding(properties.getProviders().getTencent().getModels().getChat())
            .transcription(properties.getProviders().getTencent().getModels().getTranscription())
            .image(properties.getProviders().getTencent().getModels().getImage())
            .build();

        return ProviderConfig.builder()
            .apiKey(properties.getProviders().getTencent().getApiKey())
            .baseUrl(properties.getProviders().getTencent().getBaseUrl())
            .models(models)
            .build();
    }

    // ------------------------------------------------------------------------------
    // AOT
    // ------------------------------------------------------------------------------

    public static class AiRuntimeHints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(@NonNull RuntimeHints hints, ClassLoader classLoader) {
            log.info("Register RuntimeHints by AiRuntimeHints");
            var mcs = MemberCategory.values();
            for (var type : Set.of(CommonTools.class)) {
                hints.reflection().registerType(type, mcs);
            }
        }
    }

}
