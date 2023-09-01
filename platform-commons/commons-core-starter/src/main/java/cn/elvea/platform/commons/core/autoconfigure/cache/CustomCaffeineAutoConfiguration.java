package cn.elvea.platform.commons.core.autoconfigure.cache;

import cn.elvea.platform.commons.core.autoconfigure.cache.properties.CustomCacheProperties;
import cn.elvea.platform.commons.core.autoconfigure.cache.properties.CustomCaffeineProperties;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.cache.service.CaffeineCacheService;
import cn.elvea.platform.commons.core.cache.service.CaffeineCacheServiceImpl;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CustomCacheProperties.class, CustomCaffeineProperties.class})
@ConditionalOnProperty(prefix = CustomCaffeineProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomCaffeineAutoConfiguration {

    public CustomCaffeineAutoConfiguration() {
        log.debug("CustomCaffeineAutoConfiguration......");
    }

    /**
     * @see CaffeineCacheService
     */
    @Bean
    @ConditionalOnMissingBean(CaffeineCacheService.class)
    public CaffeineCacheService caffeineCacheService(CustomCacheProperties properties) {
        return new CaffeineCacheServiceImpl(properties.getCacheNullValue(), properties.getBatchSize());
    }

    /**
     * @see CacheService
     */
    @Bean
    @ConditionalOnProperty(prefix = CustomCacheProperties.PREFIX, name = "type", havingValue = "caffeine", matchIfMissing = true)
    public CacheService cacheService(CaffeineCacheService caffeineCacheService) {
        return caffeineCacheService;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomCaffeineProperties.PREFIX, name = "spring-cache-enabled", havingValue = "true")
    public CacheManager cacheManager(CustomCacheProperties properties) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .recordStats()
                .initialCapacity(500)
                .expireAfterWrite(properties.getTimeToLive());
        cacheManager.setAllowNullValues(properties.getCacheNullValue());
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

}
