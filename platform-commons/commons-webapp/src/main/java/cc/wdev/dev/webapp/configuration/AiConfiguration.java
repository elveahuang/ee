package cc.wdev.dev.webapp.configuration;

import cc.wdev.dev.webapp.ai.tools.CoreTools;
import cc.wdev.platform.commons.ai.tools.CommonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AiConfiguration {

    @Bean
    public CommonTools commonTools() {
        return new CommonTools();
    }

    @Bean
    public ToolCallbackProvider commonToolsProvider(CommonTools commonTools, CoreTools coreTools) {
        return MethodToolCallbackProvider.builder().toolObjects(commonTools, coreTools).build();
    }

}
