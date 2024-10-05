package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CustomCacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CustomCacheProperties.class})
@ConditionalOnProperty(prefix = CustomCacheProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({CustomRedisAutoConfiguration.class, CustomRedissonAutoConfiguration.class,})
public class CustomCacheAutoConfiguration {

    public CustomCacheAutoConfiguration(CustomCacheProperties properties) {
        log.info("CustomCacheAutoConfiguration......");
        log.info("Current Cache type - {}", properties.getType());
    }

}
