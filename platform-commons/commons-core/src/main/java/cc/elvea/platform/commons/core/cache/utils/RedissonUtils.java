package cc.elvea.platform.commons.core.cache.utils;

import cc.elvea.platform.commons.enums.RateLimitTypeEnum;
import cc.elvea.platform.commons.message.socket.message.SocketMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;

import java.time.Duration;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class RedissonUtils {

    private final RedissonClient client;

    public long rateLimiter(String key, RateLimitTypeEnum type, long rate, long rateInterval) {
        RateType rateType = RateType.OVERALL;
        if (type == RateLimitTypeEnum.CLUSTER) {
            rateType = RateType.PER_CLIENT;
        }

        RRateLimiter rateLimiter = this.client.getRateLimiter(key);
        rateLimiter.trySetRate(rateType, rate, Duration.ofSeconds(rateInterval));
        if (rateLimiter.tryAcquire()) {
            return rateLimiter.availablePermits();
        } else {
            return -1L;
        }
    }

    public RTopic getTopic(String listener) {
        return this.client.getTopic(listener);
    }

    public RFuture<Long> publishAsync(SocketMessage message) {
        RTopic topic = getTopic(message.getType());
        return topic.publishAsync(message);
    }

}
