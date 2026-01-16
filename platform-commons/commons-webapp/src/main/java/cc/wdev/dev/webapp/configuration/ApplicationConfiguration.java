package cc.wdev.dev.webapp.configuration;

import cc.wdev.dev.webapp.ai.tools.CoreTools;
import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.tools.CommonTools;
import cc.wdev.platform.commons.core.tenant.Tenant;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class ApplicationConfiguration {

    @Bean
    public TenantStore tenantStore() {
        return new TenantStore() {
            @Override
            public Tenant findById(Long id) {
                return Tenant.builder().id(1L).build();
            }

            @Override
            public Tenant findByCode(String code) {
                return Tenant.builder().id(1L).build();
            }
        };
    }

    // ----------------------------------------------------------------
    // AI
    // ----------------------------------------------------------------

    @Bean
    public CommonTools commonTools() {
        return new CommonTools();
    }

    @Bean
    public CoreTools coreTools() {
        return new CoreTools();
    }

    @Bean
    public AiCustomizer aiCustomizer(CommonTools commonTools, CoreTools coreTools) {
        List<Object> tools = List.of(commonTools, coreTools);
        return AiCustomizer.builder().tools(tools).build();
    }

    @Bean
    public ToolCallbackProvider commonToolsProvider(CommonTools commonTools, CoreTools coreTools) {
        return MethodToolCallbackProvider.builder().toolObjects(commonTools, coreTools).build();
    }

}
