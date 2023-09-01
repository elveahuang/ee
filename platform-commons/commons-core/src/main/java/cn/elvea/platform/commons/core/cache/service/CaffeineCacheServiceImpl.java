package cn.elvea.platform.commons.core.cache.service;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.lock.CaffeineDistributedLock;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @see CacheService
 * @see CaffeineCacheService
 * @see AbstractCacheService
 * @since 0.0.1
 */
public class CaffeineCacheServiceImpl extends AbstractCacheService implements CaffeineCacheService {

    private final Map<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>(64);

    public CaffeineCacheServiceImpl(boolean cacheNullValues, int batchSize) {
        this.cacheNullValues = cacheNullValues;
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
        Cache<String, Object> cache = cacheMap.get(key);
        if (cache == null) {
            return Boolean.FALSE;
        }
        cache.cleanUp();
        return cache.estimatedSize() > 0;
    }

    /**
     * @see CacheService#rename(String, String)
     */
    @Override
    public void rename(@NonNull String oldKey, @NonNull String newKey) {
        this.cacheMap.put(newKey, this.cacheMap.get(oldKey));
        this.cacheMap.remove(oldKey);
    }

    /**
     * @see CacheService#move(String, int)
     */
    @Override
    public Boolean move(@NonNull String key, int database) {
        return Boolean.TRUE;
    }

    /**
     * @see CacheService#persist(String)
     */
    @Override
    public Boolean persist(@NonNull String key) {
        return Boolean.TRUE;
    }

    /**
     * @see CacheService#keys(String)
     */
    @Override
    public Set<String> keys(@NonNull String pattern) {
        return Collections.emptySet();
    }

    /**
     * @see CacheService#scan(String)
     */
    @Override
    public Set<String> scan(@NonNull String pattern) {
        return Collections.emptySet();
    }

    /**
     * @see CacheService#scan(String, int)
     */
    @Override
    public Set<String> scan(@NonNull String pattern, int limit) {
        return Collections.emptySet();
    }

    /**
     * @see CacheService#type(String)
     */
    @Override
    public String type(@NonNull String key) {
        return "caffeine";
    }

    /**
     * @see CacheService#flushDb()
     */
    @Override
    public void flushDb() {
        this.cacheMap.clear();
    }

    /**
     * @see CacheService#flushAll()
     */
    @Override
    public void flushAll() {
        this.cacheMap.clear();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 设置过期
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#expire(String, long, TimeUnit)
     */
    @Override
    public Boolean expire(@NonNull String key, long expire, TimeUnit timeUnit) {
        return null;
    }

    /**
     * @see CacheService#expireAt(String, Date)
     */
    @Override
    public Boolean expireAt(@NonNull String key, @NonNull Date date) {
        return null;
    }

    /**
     * @see CacheService#getExpire(String)
     */
    @Override
    public Long getExpire(@NonNull String key) {
        return null;
    }

    /**
     * @see CacheService#getExpireMill(String)
     */
    @Override
    public Long getExpireMill(@NonNull String key) {
        return null;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 删除操作
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#delete(List)
     */
    @Override
    public Long delete(@NonNull List<String> keys) {
        return unlink(keys);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 异步删除缓存
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#unlink(List)
     */
    @Override
    public Long unlink(@NonNull List<String> keys) {
        if (!CollectionUtils.isEmpty(keys)) {
            keys.forEach((k) -> {
                if (this.cacheMap.containsKey(k)) {
                    this.cacheMap.get(k).invalidateAll();
                }
                this.cacheMap.remove(k);
            });
        }
        return (long) keys.size();
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
            Cache<String, Object> cache = createCaffeineCache();
            cache.put(key, value);
            cacheMap.put(key, cache);
        }
    }

    /**
     * @see CacheService#set(String, Object, Duration)
     */
    @Override
    public void set(@NonNull String key, Object value, Duration duration) {
        if (this.allowCacheValues(value)) {
            if (this.allowCacheValues(value)) {
                Cache<String, Object> cache = createCaffeineCache(duration);
                cache.put(key, value);
                cacheMap.put(key, cache);
            }
        }
    }

    /**
     * @see CacheService#multiSet(Map)
     */
    @Override
    public void multiSet(@NonNull Map<String, Object> map) {
        Map<String, Object> mSetMap = mSetMap(map);
        mSetMap.forEach((k, v) -> {
            Cache<String, Object> cache = createCaffeineCache();
            cache.put(k, v);
            cacheMap.put(k, cache);
        });
    }

    /**
     * @see CacheService#multiSet(Map, Duration)
     */
    @Override
    public void multiSet(@NonNull Map<String, Object> map, Duration duration) {
        Map<String, Object> mSetMap = mSetMap(map);
        mSetMap.forEach((k, v) -> {
            Cache<String, Object> cache = createCaffeineCache(duration);
            cache.put(k, v);
            cacheMap.put(k, cache);
        });
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
        Cache<String, Object> cache = cacheMap.get(key);
        if (cache == null) {
            return null;
        }
        T value = (T) cache.getIfPresent(key);
        if (isCacheNullValues(value)) {
            set(key, newNullValue());
        }
        return returnVal(value);
    }

    /**
     * @see CacheService#get(CacheKey, Function)
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T get(@NonNull CacheKey cacheKey, Function<CacheKey, T> loader) {
        Cache<String, Object> cache = cacheMap.get(cacheKey.getKey());

        T value = (cache != null) ? (T) cache.getIfPresent(cacheKey.getKey()) : null;
        if (value == null) {
            value = loader.apply(cacheKey);
            this.set(cacheKey.getKey(), value);
        }
        return returnVal(value);
    }

    /**
     * @see CacheService#multiGet(Collection)
     */
    @Override
    public <T> List<T> multiGet(@NonNull Collection<String> keys) {
        Map<String, T> valueMap = Maps.newHashMap();
        keys.forEach((k) -> valueMap.put(k, this.get(k)));
        return CollectionUtils.isEmpty(valueMap) ?
                Collections.emptyList() : valueMap.values().stream().map(this::returnVal).collect(Collectors.toList());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 分布式锁
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see CacheService#getLock(String)
     */
    @Override
    public Lock getLock(String key) {
        return new CaffeineDistributedLock(key, 0);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 辅助方法
    // -----------------------------------------------------------------------------------------------------------------

    protected Cache<String, Object> createCaffeineCache() {
        return Caffeine.newBuilder().build();
    }

    protected Cache<String, Object> createCaffeineCache(Duration duration) {
        return Caffeine.newBuilder().expireAfterWrite(duration).build();
    }

}
