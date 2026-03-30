package cc.wdev.platform.system.configuration;

import cc.wdev.platform.system.ai.service.AiChatMemoryService;
import cc.wdev.platform.system.ai.service.GreetingService;
import cc.wdev.platform.system.ai.support.JpaChatMemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class SystemAiConfiguration {

    @Bean
    public ChatMemoryRepository chatMemoryRepository(AiChatMemoryService aiChatMemoryService) {
        return new JpaChatMemoryRepository(aiChatMemoryService);
    }

    @Bean
    public ToolCallbackProvider greetingTools(GreetingService greetingService) {
        return MethodToolCallbackProvider.builder().toolObjects(greetingService).build();
    }

}
