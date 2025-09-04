package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AiAliyunProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.AiProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.AiTencentProperties;
import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.AiFactoryImpl;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunConfig;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunFactory;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunFactoryImpl;
import cc.wdev.platform.commons.core.ai.chat.ChatCompletionService;
import cc.wdev.platform.commons.core.ai.chat.DefaultChatCompletionService;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentConfig;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentFactory;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentFactoryImpl;
import cc.wdev.platform.commons.core.ai.tools.CommonTools;
import com.alibaba.dashscope.Version;
import com.tencentcloudapi.hunyuan.v20230901.HunyuanClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestClient;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStoreOptions;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.lang.NonNull;

import java.util.Set;

import static org.springframework.ai.vectorstore.elasticsearch.SimilarityFunction.cosine;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ChatModel.class, ChatMemoryRepository.class, MessageWindowChatMemory.class})
@ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({AiProperties.class, AiTencentProperties.class, AiAliyunProperties.class})
@ImportRuntimeHints(AiAutoConfiguration.AiRuntimeHints.class)
public class AiAutoConfiguration {

    public AiAutoConfiguration(AiProperties properties) {
        log.info("AiAutoConfiguration is enabled");
        log.info("AiAutoConfiguration Service Provider - {}", properties.getServiceProvider());
        log.info("AiAutoConfiguration Vector Store Provider - {}", properties.getVectorStoreProvider());
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageWindowChatMemory messageWindowChatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(10)
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatCompletionService chatCompletionService(OpenAiChatModel model,
                                                       MessageWindowChatMemory messageWindowChatMemory,
                                                       ObjectProvider<AiCustomizer> customizerProvider) {
        return new DefaultChatCompletionService(model, messageWindowChatMemory, customizerProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public AiFactory aiFactory(AiProperties aiProperties,
                               ChatCompletionService defaultChatCompletionService,
                               MessageWindowChatMemory messageWindowChatMemory) {
        return new AiFactoryImpl(aiProperties.getServiceProvider(), defaultChatCompletionService, messageWindowChatMemory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({VectorStore.class})
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "vector-store.provider", havingValue = "elastic")
    public VectorStore vectorStore(RestClient restClient, EmbeddingModel embeddingModel) {
        ElasticsearchVectorStoreOptions options = new ElasticsearchVectorStoreOptions();
        options.setIndexName("custom-index");
        options.setSimilarity(cosine);
        options.setDimensions(1536);

        return ElasticsearchVectorStore.builder(restClient, embeddingModel)
            .options(options)
            .initializeSchema(true)
            .batchingStrategy(new TokenCountBatchingStrategy())
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({VectorStore.class})
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "vector-store.provider", havingValue = "simple", matchIfMissing = true)
    public VectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    /**
     * 阿里云 - 义气大模型
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({Version.class})
    @ConditionalOnProperty(prefix = AiTencentProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public AiTencentFactory aiTencentFactory(AiTencentProperties properties) {
        AiTencentConfig config = AiTencentConfig.builder().apiKey(properties.getApiKey()).build();
        return new AiTencentFactoryImpl(config);
    }

    /**
     * 腾讯云 - 混元大模型
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({HunyuanClient.class})
    @ConditionalOnProperty(prefix = AiAliyunProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public AiAliyunFactory aiAliyunFactory(AiAliyunProperties properties) {
        AiAliyunConfig config = AiAliyunConfig.builder().apiKey(properties.getApiKey()).build();
        return new AiAliyunFactoryImpl(config);
    }

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
