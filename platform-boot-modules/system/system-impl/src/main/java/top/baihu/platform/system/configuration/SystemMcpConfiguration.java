package top.baihu.platform.system.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.system.ai.service.GreetingService;

/**
 * @author elvea
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class SystemMcpConfiguration {

    @Bean
    public ToolCallbackProvider greetingTools(GreetingService greetingService) {
        return MethodToolCallbackProvider.builder().toolObjects(greetingService).build();
    }

}
