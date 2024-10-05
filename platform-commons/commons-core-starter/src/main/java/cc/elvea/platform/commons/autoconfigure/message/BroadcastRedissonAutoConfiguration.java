package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.BroadcastProperties;
import cc.elvea.platform.commons.message.broadcast.redisson.RedissonBroadcastListener;
import cc.elvea.platform.commons.message.broadcast.redisson.RedissonBroadcastSender;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = BroadcastProperties.PREFIX_REDISSON, name = "enabled", havingValue = "true")
public class BroadcastRedissonAutoConfiguration {

    public BroadcastRedissonAutoConfiguration() {
        log.info("BroadcastRedissonAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonBroadcastSender broadcastSender(BroadcastProperties properties, RedissonClient client) {
        return new RedissonBroadcastSender(client, properties.getRedisson());
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonBroadcastListener redissonBroadcastListener(BroadcastProperties properties,
                                                               RedissonClient client,
                                                               ApplicationEventPublisher publisher) {
        return new RedissonBroadcastListener(client, properties.getRedisson(), publisher);
    }

}
