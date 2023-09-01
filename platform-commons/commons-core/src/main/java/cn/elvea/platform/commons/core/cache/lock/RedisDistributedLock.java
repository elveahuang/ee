package cn.elvea.platform.commons.core.cache.lock;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.commons.core.utils.StringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
@Slf4j
public class RedisDistributedLock implements Lock {

    private final String luaScript = """
            if redis.call('get',KEYS[1]) == ARGV[1] then
                return redis.call('del',KEYS[1])
            else
                return 0
            end
            """;

    private final StringRedisTemplate stringRedisTemplate;

    private final String key;

    private final int leaseMilliseconds;

    private final ThreadLocal<String> values = new ThreadLocal<>();

    public RedisDistributedLock(@NonNull StringRedisTemplate stringRedisTemplate, @NonNull String key, int leaseMilliseconds) {
        if (leaseMilliseconds <= 0) {
            throw new IllegalArgumentException("Parameter 'leaseMilliseconds' must grate then 0: " + leaseMilliseconds);
        }

        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
        this.leaseMilliseconds = leaseMilliseconds;
    }

    @Override
    public void lock() {
        boolean result = this.tryLock();
        log.info("RedisDistributedLock lock {}.", result ? "success" : "fail");
    }

    @Override
    public void lockInterruptibly() {
        boolean result = this.tryLock();
        log.info("RedisDistributedLock lock {}.", result ? "success" : "fail");
    }

    @Override
    public boolean tryLock() {
        String value = values.get();
        if (value == null || value.isEmpty()) {
            value = StringUtils.uuid();
            values.set(value);
        }

        final byte[] keyBytes = key.getBytes(GlobalConstants.UTF8);
        final byte[] valueBytes = value.getBytes(GlobalConstants.UTF8);
        List<Object> redisResults = this.stringRedisTemplate.executePipelined((RedisCallback<String>) connection -> {
            connection.stringCommands().set(keyBytes, valueBytes, Expiration.milliseconds(leaseMilliseconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
            connection.stringCommands().get(keyBytes);
            return null;
        });
        Object currentLockSecret = redisResults.size() > 1 ? redisResults.get(1) : redisResults.get(0);
        return currentLockSecret != null && currentLockSecret.toString().equals(value);
    }

    @Override
    public boolean tryLock(@NonNull long time, @NonNull TimeUnit unit) throws InterruptedException {
        long waitMillis = unit.toMillis(time);
        boolean locked = tryLock();
        while (!locked && waitMillis > 0) {
            long sleep = waitMillis < 1000 ? waitMillis : 1000;
            Thread.sleep(sleep);
            waitMillis -= sleep;
            locked = tryLock();
        }
        return locked;
    }

    @Override
    public void unlock() {
        if (values.get() != null) {
            RedisScript<Long> script = new DefaultRedisScript<>(this.luaScript, Long.class);
            this.stringRedisTemplate.execute(script, List.of(key), values.get());
            values.remove();
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

}
