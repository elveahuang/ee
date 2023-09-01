package cn.elvea.platform.commons.core.autoconfigure.log;

import cn.elvea.platform.commons.core.autoconfigure.core.CommonsAutoConfiguration;
import cn.elvea.platform.commons.core.autoconfigure.log.properties.LogProperties;
import cn.elvea.platform.commons.core.log.LogManager;
import cn.elvea.platform.commons.core.log.LogManagerCustomizer;
import cn.elvea.platform.commons.core.log.aspect.OperationLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(LogProperties.class)
@AutoConfigureAfter(CommonsAutoConfiguration.class)
public class LogAutoConfiguration {

    public LogAutoConfiguration() {
        log.info("LogAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogManager logManager(ObjectProvider<LogManagerCustomizer> customizers) {
        LogManager logManager = new LogManager();
        customizers.orderedStream().forEach((customizer) -> customizer.customize(logManager));
        return logManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(LogManager.class)
    public OperationLogAspect optLogAspect(LogManager manager) {
        return new OperationLogAspect(manager);
    }

}
