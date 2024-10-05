package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.elvea.platform.commons.core.log.aspect.OperationLogAspect;
import cc.elvea.platform.commons.core.log.interceptor.UrlLogInterceptor;
import cc.elvea.platform.commons.core.log.store.DefaultLogStore;
import cc.elvea.platform.commons.core.log.store.LogStore;
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
@EnableConfigurationProperties(CoreProperties.LogProperties.class)
@ConditionalOnProperty(prefix = CoreProperties.LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
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

    @Bean
    public UrlLogInterceptor urlLogInterceptor(LogStore logStore) {
        return new UrlLogInterceptor(logStore);
    }

}
