package top.baihu.platform.system.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.core.ai.AiCustomizer;
import top.baihu.platform.commons.core.ai.tools.CommonTools;
import top.baihu.platform.system.ai.service.AiChatMemoryService;
import top.baihu.platform.system.ai.support.JpaChatMemoryRepository;

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
