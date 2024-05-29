package cc.elvea.platform.commons.autoconfigure.logging;

import cc.elvea.platform.commons.autoconfigure.core.CoreAutoConfiguration;
import cc.elvea.platform.commons.autoconfigure.logging.properties.LogProperties;
import cc.elvea.platform.commons.logging.aspect.OperationLogAspect;
import cc.elvea.platform.commons.logging.store.DefaultLogStore;
import cc.elvea.platform.commons.logging.store.LogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(CoreAutoConfiguration.class)
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    public LogAutoConfiguration() {
        log.info("LogAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogStore logStore() {
        return new DefaultLogStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogAspect optLogAspect(LogStore logStore) {
        log.info("Creating OperationLogAspect...");
        return new OperationLogAspect(logStore);
    }

}
