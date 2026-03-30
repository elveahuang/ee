package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.CacheCustomProperties;
import cc.wdev.platform.commons.core.cache.NullValue;
import cc.wdev.platform.commons.core.cache.aspect.RateLimitAspect;
import cc.wdev.platform.commons.core.cache.service.CacheService;
import cc.wdev.platform.commons.core.cache.service.RedissonCacheService;
import cc.wdev.platform.commons.core.cache.utils.RedissonUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJackson3Codec;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeHint;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.data.redis.autoconfigure.DataRedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.context.annotation.Primary;

import java.util.function.Consumer;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass({RedissonClient.class})
@EnableConfigurationProperties({CacheCustomProperties.class, DataRedisProperties.class})
@ConditionalOnProperty(prefix = CacheCustomProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@ImportRuntimeHints(CacheCustomAutoConfiguration.CacheRuntimeHints.class)
public class CacheCustomAutoConfiguration {

    public CacheCustomAutoConfiguration() {
        log.info("CacheCustomAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public Codec codec() {
        return new JsonJackson3Codec();
    }

    @Bean
    @ConditionalOnMissingBean(RedissonUtils.class)
    public RedissonUtils redissonUtils(RedissonClient redissonClient) {
        log.info("Creating redissonUtils");
        return new RedissonUtils(redissonClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonAutoConfigurationCustomizer redissonAutoConfigurationCustomizer(Codec codec) {
        return config -> config.setCodec(codec);
    }

    @Bean
    @ConditionalOnMissingBean(RedissonCacheService.class)
    public CacheService cacheService(RedissonClient redissonClient, RedissonUtils redissonUtils, CacheCustomProperties properties) {
        log.info("Creating cacheService");
        return new RedissonCacheService(redissonClient, redissonUtils, properties.isCacheNullValue(), properties.getBatchSize());
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CacheManager cacheManager(CacheCustomProperties properties, RedissonClient redissonClient) {
        log.info("Creating cacheManager");
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient);
        cacheManager.setAllowNullValues(properties.isCacheNullValue());
        return cacheManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(CacheService.class)
    public RateLimitAspect rateLimitAspect(CacheService cacheService) {
        log.info("Creating rateLimitAspect");
        return new RateLimitAspect(cacheService);
    }

    static class CacheRuntimeHints implements RuntimeHintsRegistrar {

        private static final Consumer<TypeHint.Builder> INVOKE_DECLARED_CONSTRUCTORS = TypeHint.builtWith(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection().registerType(NullValue.class, INVOKE_DECLARED_CONSTRUCTORS);
        }

    }

}
