package cn.elvea.platform.commons.core.cache.service;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.lock.RedisDistributedLock;
import com.google.common.collect.Lists;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see CacheService
 * @see RedisCacheService
 * @see AbstractCacheService
 * @since 0.0.1
 */
public class RedisCacheServiceImpl extends AbstractCacheService implements RedisCacheService {

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueOps;

    public RedisCacheServiceImpl(RedisTemplate<String, Object> redisTemplate,
                                 StringRedisTemplate stringRedisTemplate,
                                 boolean cacheNullValue,
                                 int batchSize) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.valueOps = redisTemplate.opsForValue();
        this.cacheNullValues = cacheNullValue;
        this.batchSize = batchSize;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 基础方法
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#exists(String)
     */
    @Override
    public Boolean exists(@NonNull String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @see CacheService#rename(String, String)
     */
    @Override
    public void rename(@NonNull String oldKey, @NonNull String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * @see CacheService#move(String, int)
     */
    @Override
    public Boolean move(@NonNull String key, int database) {
        return redisTemplate.move(key, database);
    }

    /**
     * @see CacheService#persist(String)
     */
    @Override
    public Boolean persist(@NonNull String key) {
        return redisTemplate.persist(key);
    }

    /**
     * @see CacheService#keys(String)
     */
    @Override
    public Set<String> keys(@NonNull String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * @see CacheService#scan(String)
     */
    @Override
    public Set<String> scan(@NonNull String pattern) {
        return scan(pattern, batchSize);
    }

    /**
     * @see CacheService#scan(String, int)
     */
    @Override
    public Set<String> scan(@NonNull final String pattern, final int limit) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keySet = new HashSet<>();

            ScanOptions options;
            if (limit <= 0) {
                options = ScanOptions.scanOptions().match(pattern).count(1000).build();
            } else {
                options = ScanOptions.scanOptions().match(pattern).count(limit).build();
            }

            Cursor<byte[]> cursor = connection.commands().scan(options);
            while (cursor.hasNext()) {
                keySet.add(new String(cursor.next()));
            }
            return keySet;
        });
    }

    /**
     * @see CacheService#type(String)
     */
    @Override
    public String type(@NonNull String key) {
        DataType type = redisTemplate.type(key);
        return type == null ? DataType.NONE.code() : type.code();
    }

    /**
     * @see CacheService#flushDb()
     */
    @Override
    public void flushDb() {
        this.redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.commands().flushDb();
            return "ok";
        });
    }

    /**
     * @see CacheService#flushAll()
     */
    @Override
    public void flushAll() {
        this.redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.commands().flushAll();
            return "ok";
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 设置过期
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#expire(String, long, TimeUnit)
     */
    @Override
    public Boolean expire(@NonNull String key, long expire, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expire, timeUnit);
    }

    /**
     * @see CacheService#expireAt(String, Date)
     */
    @Override
    public Boolean expireAt(@NonNull String key, @NonNull Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * @see CacheService#getExpire(String)
     */
    @Override
    public Long getExpire(@NonNull String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * @see CacheService#getExpireMill(String)
     */
    @Override
    public Long getExpireMill(@NonNull String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 删除操作
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#delete(List)
     */
    @Override
    public Long delete(@NonNull List<String> keys) {
        long total = 0;
        if (!CollectionUtils.isEmpty(keys)) {
            List<List<String>> partitionKeys = Lists.partition(keys, this.batchSize);
            for (List<String> list : partitionKeys) {
                Long count = redisTemplate.delete(list);
                if (count != null) {
                    total += count;
                }
            }
        }
        return total;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 异步删除缓存
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#unlink(List)
     */
    @Override
    public Long unlink(@Nullable List<String> keys) {
        long total = 0;
        if (!CollectionUtils.isEmpty(keys)) {
            List<List<String>> partitionKeys = Lists.partition(keys, this.batchSize);
            for (List<String> list : partitionKeys) {
                Long count = redisTemplate.unlink(list);
                if (count != null) {
                    total += count;
                }
            }
        }
        return total;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 设置缓存
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#set(String, Object)
     */
    @Override
    public void set(@NonNull String key, Object value) {
        if (this.allowCacheValues(value)) {
            valueOps.set(key, ObjectUtils.isEmpty(value) ? newNullValue() : value);
        }
    }

    /**
     * @see CacheService#set(String, Object, Duration)
     */
    @Override
    public void set(@NonNull String key, Object value, Duration duration) {
        if (this.allowCacheValues(value)) {
            valueOps.set(key, ObjectUtils.isEmpty(value) ? newNullValue() : value, duration);
        }
    }

    /**
     * @see CacheService#multiSet(Map)
     */
    @Override
    public void multiSet(@NonNull Map<String, Object> map) {
        Map<String, Object> mSetMap = mSetMap(map);
        valueOps.multiSet(mSetMap);
    }

    /**
     * @see CacheService#multiSet(Map, Duration)
     */
    @Override
    public void multiSet(@NonNull Map<String, Object> map, Duration duration) {
        Map<String, Object> mSetMap = mSetMap(map);
        valueOps.multiSet(mSetMap);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 获取缓存
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#get(String)
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T get(@NonNull String key) {
        T value = (T) valueOps.get(key);
        if (isCacheNullValues(value)) {
            set(key, newNullValue());
        }
        return returnVal(value);
    }

    /**
     * @see CacheService#get(CacheKey, Function)
     */
    @Nullable
    @SuppressWarnings({"unchecked"})
    public <T> T get(@NonNull CacheKey cacheKey, Function<CacheKey, T> loader) {
        T value = (T) valueOps.get(cacheKey.getKey());
        if (ObjectUtils.isEmpty(value)) {
            Lock lock = getLock(cacheKey.getKey() + "-lock");
            try {
                lock.lock();
                value = (T) valueOps.get(cacheKey.getKey());
                if (value != null) {
                    return returnVal(value);
                }
                value = loader.apply(cacheKey);
                this.set(cacheKey.getKey(), value);
            } finally {
                lock.unlock();
            }
        }
        return returnVal(value);
    }

    /**
     * @see CacheService#multiGet(Collection)
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public <T> List<T> multiGet(@NonNull Collection<String> keys) {
        List<T> list = (List<T>) valueOps.multiGet(keys);
        return CollectionUtils.isEmpty(list) ?
                Collections.emptyList() : list.stream().map(this::returnVal).collect(Collectors.toList());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 分布式锁
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#getLock(String)
     */
    @Override
    public Lock getLock(String key) {
        return new RedisDistributedLock(this.stringRedisTemplate, key, 10 * 60);
    }

}
