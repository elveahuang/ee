package cc.elvea.platform.commons.core.cache.utils;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
public class RedisUtils {
    private final static String SCRIPT = """
            local c
            c = redis.call('get', KEYS[1])
            if c and tonumber(c) > tonumber(ARGV[1]) then
            return c;
            end
            c = redis.call('incr', KEYS[1])
            if tonumber(c) == 1 then
            redis.call('expire', KEYS[1], ARGV[2])
            end
            return c;
            """;

    private final RedisTemplate<String, Object> redisTemplate;

    public Long trySetRate(String key, long rate, long rateInterval) {
        List<String> keys = ImmutableList.of(key);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(SCRIPT, Long.class);
        return redisTemplate.execute(redisScript, keys, rate, rateInterval);
    }

}
