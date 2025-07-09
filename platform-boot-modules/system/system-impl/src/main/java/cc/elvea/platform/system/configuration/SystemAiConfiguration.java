package cc.elvea.platform.system.configuration;

import cc.elvea.platform.commons.core.ai.AiCustomizer;
import cc.elvea.platform.commons.core.ai.tools.CommonTools;
import cc.elvea.platform.system.ai.service.AiChatMemoryService;
import cc.elvea.platform.system.ai.support.JpaChatMemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
    public AiCustomizer aiCustomizer() {
        List<Object> tools = List.of(new CommonTools());
        return AiCustomizer.builder().tools(tools).build();
    }

}
