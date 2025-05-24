package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.JsonRpcProperties;
import com.xxl.tool.jsonrpc.JsonRpcServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({JsonRpcProperties.class})
@ConditionalOnClass({JsonRpcServer.class})
@ConditionalOnProperty(prefix = JsonRpcProperties.PREFIX, name = "enabled", havingValue = "true")
public class JsonRpcAutoConfiguration {

    public JsonRpcAutoConfiguration() {
        log.info("JsonRpcAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public JsonRpcServer jsonRpcServer() {
        return new JsonRpcServer();
    }

}
