package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.RateLimitProperties;
import cc.elvea.platform.commons.core.cache.aspect.RateLimitAspect;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@EnableConfigurationProperties(RateLimitProperties.class)
@ConditionalOnProperty(prefix = RateLimitProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RateLimitAutoConfiguration {

    public RateLimitAutoConfiguration() {
        log.info("RateLimitAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(CacheService.class)
    public RateLimitAspect rateLimitAspect(CacheService cacheService) {
        log.info("Creating RateLimitAspect...");
        return new RateLimitAspect(cacheService);
    }

}
