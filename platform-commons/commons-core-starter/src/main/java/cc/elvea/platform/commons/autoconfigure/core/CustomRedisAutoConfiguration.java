package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CustomCacheProperties;
import cc.elvea.platform.commons.autoconfigure.core.properties.CustomRedisProperties;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.core.cache.service.RedisCacheService;
import cc.elvea.platform.commons.core.cache.service.RedisCacheServiceImpl;
import cc.elvea.platform.commons.core.cache.utils.RedisUtils;
import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CustomRedisProperties.class, CustomCacheProperties.class})
@ConditionalOnClass({RedisAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomRedisProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomRedisAutoConfiguration {

    public CustomRedisAutoConfiguration() {
        log.info("CustomRedisAutoConfiguration......");
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisSerializer<Object> redisObjectSerializer() {
        return new Jackson2JsonRedisSerializer<>(JacksonUtils.getCacheObjectMapper(), Object.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisSerializer redisKeySerializer() {
        return new StringRedisSerializer();
    }

    @Bean("stringRedisTemplate")
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
                                                       RedisSerializer<Object> valueSerializer,
                                                       StringRedisSerializer keySerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @see RedisUtils
     */
    @Bean
    @ConditionalOnMissingBean(RedisUtils.class)
    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtils(redisTemplate);
    }

    /**
     * @see RedisCacheService
     */
    @Bean
    @ConditionalOnMissingBean(RedisCacheService.class)
    public RedisCacheService redisCacheService(RedisTemplate<String, Object> redisTemplate,
                                               StringRedisTemplate stringRedisTemplate,
                                               RedisUtils redisUtils,
                                               CustomCacheProperties properties) {
        return new RedisCacheServiceImpl(redisTemplate, stringRedisTemplate, redisUtils, properties.isCacheNullValue(), properties.getBatchSize());
    }

    /**
     * @see CacheService
     */
    @Bean
    @Primary
    @ConditionalOnProperty(prefix = CustomCacheProperties.PREFIX, name = "type", havingValue = "redis")
    public CacheService cacheService(RedisCacheService redisCacheService) {
        return redisCacheService;
    }

    /**
     * @see CacheManager
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomRedisProperties.PREFIX, name = "spring-cache-enabled", havingValue = "true")
    public CacheManager cacheManager(CustomCacheProperties properties,
                                     RedisConnectionFactory factory,
                                     RedisSerializer<Object> valueSerializer,
                                     StringRedisSerializer keySerializer) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(properties.getTimeToLive())
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));
        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }

}
