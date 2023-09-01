package cn.elvea.platform.commons.core.autoconfigure.data.core;

import cn.elvea.platform.commons.core.autoconfigure.data.core.properties.CustomDataProperties;
import cn.elvea.platform.commons.core.data.jpa.auditor.UserAuditorAware;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties(CustomDataProperties.class)
@ConditionalOnProperty(prefix = CustomDataProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomDataAutoConfiguration {

    public CustomDataAutoConfiguration() {
        log.info("CustomDataAutoConfiguration is enabled.");
    }

    /**
     * @see UserAuditorAware
     */
    @Bean
    @ConditionalOnMissingBean
    public UserAuditorAware userAuditorAware() {
        return new UserAuditorAware();
    }

}
