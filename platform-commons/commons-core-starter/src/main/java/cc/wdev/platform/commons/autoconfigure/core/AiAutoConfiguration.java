package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AiAliyunProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.AiProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.AiTencentProperties;
import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.AiManager;
import cc.wdev.platform.commons.core.ai.AiManagerImpl;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunConfig;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunFactory;
import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunFactoryImpl;
import cc.wdev.platform.commons.core.ai.chat.ChatService;
import cc.wdev.platform.commons.core.ai.chat.DefaultChatService;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentConfig;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentFactory;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentFactoryImpl;
import cc.wdev.platform.commons.core.ai.tools.CommonTools;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStoreOptions;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.context.annotation.Primary;

import java.util.Set;

import static org.springframework.ai.vectorstore.elasticsearch.SimilarityFunction.cosine;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.ai.vectorstore.VectorStore")
@EnableConfigurationProperties({AiProperties.class, AiTencentProperties.class, AiAliyunProperties.class})
@ImportRuntimeHints(AiAutoConfiguration.AiRuntimeHints.class)
public class AiAutoConfiguration {

    private final AiProperties aiProperties;

    public AiAutoConfiguration(AiProperties aiProperties) {
        log.info("AiAutoConfiguration is enabled");
        log.info("AiAutoConfiguration Service Provider - {}", aiProperties.getServiceProvider());
        log.info("AiAutoConfiguration Vector Store Provider - {}", aiProperties.getVectorStoreProvider());

        this.aiProperties = aiProperties;
    }

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

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ChatModel chatModel(ObjectProvider<ChatModel> objectProvider) {
        return objectProvider.getIfAvailable();
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatService chatService(ChatModel chatModel,
                                   ChatMemory chatMemory,
                                   ObjectProvider<AiCustomizer> customizerProvider) {
        return new DefaultChatService(chatModel, chatMemory, customizerProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public AiManager aiManager(ChatService chatService, ChatModel chatModel, ChatMemory chatMemory) {
        return new AiManagerImpl(aiProperties.getServiceProvider(), chatModel, chatService, chatMemory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({VectorStore.class})
    @ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "vector-store.provider", havingValue = "elastic")
    public VectorStore vectorStore(Rest5Client restClient, EmbeddingModel embeddingModel) {
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

    // ------------------------------------------------------------------------------
    // 腾讯云大模型
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiTencentProperties.PREFIX, name = "enabled", havingValue = "true")
    public AiTencentFactory aiTencentFactory(AiTencentProperties properties) {
        AiTencentConfig config = AiTencentConfig.builder().apiKey(properties.getApiKey()).build();
        return new AiTencentFactoryImpl(config);
    }

    // ------------------------------------------------------------------------------
    // 阿里云大模型
    // ------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AiAliyunProperties.PREFIX, name = "enabled", havingValue = "true")
    public AiAliyunFactory aiAliyunFactory(AiAliyunProperties properties) {
        AiAliyunConfig config = AiAliyunConfig.builder()
            .apiKey(properties.getApiKey())
            .transcription(properties.getTranscription())
            .build();
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
