package cn.elvea.platform.commons.core.autoconfigure.cache;

import cn.elvea.platform.commons.core.autoconfigure.cache.properties.CustomCacheProperties;
import cn.elvea.platform.commons.core.autoconfigure.cache.properties.CustomRedissonProperties;
import cn.elvea.platform.commons.core.cache.redisson.RedissonAutoConfigurationCustomizer;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.cache.service.RedissonCacheService;
import cn.elvea.platform.commons.core.cache.service.RedissonCacheServiceImpl;
import cn.elvea.platform.commons.core.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({RedisAutoConfiguration.class, RedissonClient.class})
@EnableConfigurationProperties({CustomRedissonProperties.class, CustomCacheProperties.class, RedisProperties.class})
@ConditionalOnProperty(prefix = CustomRedissonProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomRedissonAutoConfiguration {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";

    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    private final RedisProperties redisProperties;

    private final CustomRedissonProperties redissonProperties;

    private final List<RedissonAutoConfigurationCustomizer> redissonAutoConfigurationCustomizers;

    public CustomRedissonAutoConfiguration(RedisProperties redisProperties,
                                           CustomRedissonProperties redissonProperties,
                                           @Autowired(required = false) List<RedissonAutoConfigurationCustomizer> redissonAutoConfigurationCustomizers) {
        log.debug("CustomRedissonAutoConfiguration......");
        this.redisProperties = redisProperties;
        this.redissonProperties = redissonProperties;
        this.redissonAutoConfigurationCustomizers = redissonAutoConfigurationCustomizers;
    }

    @Bean
    @ConditionalOnMissingBean(RedissonClient.class)
    public Codec codec() {
        return new JsonJacksonCodec(JacksonUtils.getCacheObjectMapper());
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient(Codec codec) {

        int timeout;
        Duration timeoutValue = this.redisProperties.getTimeout();
        if (null == timeoutValue) {
            timeout = 10000;
        } else {
            timeout = Long.valueOf(timeoutValue.toMillis()).intValue();
        }

        Config config = new Config();
        if (redisProperties.getSentinel() != null) {
            config.useSentinelServers()
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .addSentinelAddress(convert(redisProperties.getSentinel().getNodes()))
                    .setDatabase(redisProperties.getDatabase())
                    .setConnectTimeout(timeout)
                    .setMasterConnectionPoolSize(this.redissonProperties.getMaxActive())
                    .setMasterConnectionMinimumIdleSize(this.redissonProperties.getMinIdle())
                    .setPassword(redisProperties.getPassword());
        } else if (this.redisProperties.getCluster() != null) {
            config.useClusterServers()
                    .addNodeAddress(convert(this.redisProperties.getCluster().getNodes()))
                    .setConnectTimeout(timeout)
                    .setMasterConnectionPoolSize(this.redissonProperties.getMaxActive())
                    .setMasterConnectionMinimumIdleSize(this.redissonProperties.getMinIdle())
                    .setPassword(redisProperties.getPassword());
        } else {
            String prefix = this.redisProperties.getSsl().isEnabled() ? REDISS_PROTOCOL_PREFIX : REDIS_PROTOCOL_PREFIX;
            config.useSingleServer()
                    .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                    .setConnectTimeout(timeout)
                    .setConnectionPoolSize(this.redissonProperties.getMaxActive())
                    .setConnectionMinimumIdleSize(this.redissonProperties.getMinIdle())
                    .setDatabase(redisProperties.getDatabase())
                    .setPassword(redisProperties.getPassword());
        }

        // Codec
        config.setCodec(codec);

        // 自定义配置
        if (redissonAutoConfigurationCustomizers != null) {
            for (RedissonAutoConfigurationCustomizer customizer : redissonAutoConfigurationCustomizers) {
                customizer.customize(config);
            }
        }

        return Redisson.create(config);
    }

    /**
     * @see CacheService
     */
    @Bean
    @ConditionalOnMissingBean(RedissonCacheService.class)
    public RedissonCacheService redissonCacheService(RedissonClient redissonClient, CustomCacheProperties properties) {
        return new RedissonCacheServiceImpl(redissonClient, properties.getCacheNullValue(), properties.getBatchSize());
    }

    /**
     * @see CacheService
     */
    @Bean
    @ConditionalOnProperty(prefix = CustomCacheProperties.PREFIX, name = "type", havingValue = "redisson")
    public CacheService cacheService(RedissonCacheService redissonCacheService) {
        return redissonCacheService;
    }

    /**
     * @see CacheManager
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomRedissonProperties.PREFIX, name = "spring-cache-enabled", havingValue = "true")
    public CacheManager cacheManager(CustomCacheProperties properties, RedissonClient redissonClient, Codec codec) {
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient);
        cacheManager.setCodec(codec);
        cacheManager.setAllowNullValues(properties.getCacheNullValue());
        return cacheManager;
    }

    private String[] convert(List<String> nodesObject) {
        List<String> nodes = new ArrayList<>(nodesObject.size());
        for (String node : nodesObject) {
            if (!node.startsWith(REDIS_PROTOCOL_PREFIX) && !node.startsWith(REDISS_PROTOCOL_PREFIX)) {
                nodes.add(REDIS_PROTOCOL_PREFIX + node);
            } else {
                nodes.add(node);
            }
        }
        return nodes.toArray(new String[0]);
    }

}
