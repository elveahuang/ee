package cn.elvea.platform.commons.core.autoconfigure.cache;

import cn.elvea.platform.commons.core.autoconfigure.cache.properties.CustomCacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CustomCacheProperties.class})
@ConditionalOnProperty(prefix = CustomCacheProperties.PREFIX, name = "enabled", havingValue = "true")
@Import({
        CustomRedisAutoConfiguration.class,
        CustomRedissonAutoConfiguration.class,
        CustomCaffeineAutoConfiguration.class,
})
public class CustomCacheAutoConfiguration {

    public CustomCacheAutoConfiguration(CustomCacheProperties properties) {
        log.debug("CustomCacheAutoConfiguration......");
        log.debug("Current Cache - {}", properties.getType());
    }

}
