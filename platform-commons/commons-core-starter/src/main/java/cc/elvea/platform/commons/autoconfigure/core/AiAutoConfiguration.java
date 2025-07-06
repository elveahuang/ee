package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.AiProperties;
import cc.elvea.platform.commons.core.ai.AiCustomizer;
import cc.elvea.platform.commons.core.ai.AiFactory;
import cc.elvea.platform.commons.core.ai.AiFactoryImpl;
import cc.elvea.platform.commons.core.ai.chat.ChatCompletionService;
import cc.elvea.platform.commons.core.ai.chat.DefaultChatCompletionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ChatModel.class, ChatMemoryRepository.class, MessageWindowChatMemory.class})
@ConditionalOnProperty(prefix = AiProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({AiProperties.class})
public class AiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageWindowChatMemory messageWindowChatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder().chatMemoryRepository(chatMemoryRepository).maxMessages(10).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatCompletionService chatCompletionService(DeepSeekChatModel model,
                                                       MessageWindowChatMemory messageWindowChatMemory,
                                                       ObjectProvider<AiCustomizer> customizerProvider) {
        return new DefaultChatCompletionService(model, messageWindowChatMemory, customizerProvider);
    }

    @Bean
    public AiFactory aiFactory(AiProperties aiProperties,
                               ChatCompletionService defaultChatCompletionService,
                               MessageWindowChatMemory messageWindowChatMemory) {
        return new AiFactoryImpl(aiProperties.getProvider(), defaultChatCompletionService, messageWindowChatMemory);
    }

}
