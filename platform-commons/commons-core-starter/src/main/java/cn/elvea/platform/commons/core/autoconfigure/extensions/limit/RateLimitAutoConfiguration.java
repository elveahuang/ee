package cn.elvea.platform.commons.core.autoconfigure.extensions.limit;

import cn.elvea.platform.commons.core.autoconfigure.extensions.limit.properties.RateLimitProperties;
import cn.elvea.platform.commons.core.extensions.limit.RateLimitAspect;
import lombok.extern.slf4j.Slf4j;
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
@ConditionalOnProperty(prefix = RateLimitProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(RateLimitProperties.class)
public class RateLimitAutoConfiguration {

    public RateLimitAutoConfiguration() {
        log.info("RateLimitAutoConfiguration is enabled.");
    }

    @Bean
    public RateLimitAspect rateLimitAspect() {
        return new RateLimitAspect();
    }

}
