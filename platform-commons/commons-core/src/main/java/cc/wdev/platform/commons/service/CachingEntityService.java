package cc.wdev.platform.commons.service;

import cc.wdev.platform.commons.constants.GlobalConstants;
import cc.wdev.platform.commons.core.cache.CacheKey;
import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.service.CacheService;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.utils.SpringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通用实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see EntityService
 * @see Service
 */
public interface CachingEntityService<T extends IdEntity, K extends Serializable> extends EntityService<T, K> {

    default CacheService getCacheService() {
        return SpringUtils.getBean(CacheService.class);
    }

    default int getBatchSize() {
        return GlobalConstants.MAX_BATCH_CACHE_KEY_SIZE;
    }

    default CacheKeyGenerator getCacheKeyGenerator() {
        return SimpleCacheKeyGenerator.byClassSimpleName(this.currentEntityClass());
    }

    /**
     * 获取指定Key的缓存实体
     */
    default T findByCacheKey(CacheKey cacheKey) {
        return getCacheService().get(cacheKey);
    }

    /**
     * 获取指定Key的缓存实体
     */
    default T findByCacheKey(CacheKey cacheKey, Function<CacheKey, T> loader) {
        return getCacheService().get(cacheKey, loader);
    }

    /**
     * 获取指定Key的缓存实体
     */
    default List<T> findByCacheKey(Collection<CacheKey> keys) {
        return getCacheService().multiGetByCacheKey(keys);
    }

    /**
     * 基于缓存实现获取指定主键的实体
     *
     * @param id 主键
     * @return 实体
     */
    default T findCacheById(K id) {
        CacheKey cacheKey = getCacheKeyGenerator().key("id", id);
        return getCacheService().get(cacheKey, k -> this.findById(id));
    }

    /**
     * 基于缓存实现批量获取指定主键的实体
     *
     * @param ids 主键
     * @return 实体集合
     */
    default List<T> findCacheByIds(Collection<K> ids) {
        return findCacheByIds(ids, k -> findByIds(ids));
    }

    /**
     * 基于缓存实现批量获取指定主键的实体
     *
     * @param ids    主键
     * @param loader 缓存不存在时回调获取实体
     * @return 实体集合
     */
    default List<T> findCacheByIds(Collection<K> ids, Function<Collection<K>, Collection<T>> loader) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // 批量生成缓存键
        Map<K, CacheKey> idToCacheKey = ids.stream()
            .collect(Collectors.toMap((v) -> v, getCacheKeyGenerator()::key, (e1, e2) -> e1));

        // 批量查询缓存
        List<CacheKey> cacheKeys = Lists.newArrayList(idToCacheKey.values());
        List<List<CacheKey>> partitions = Lists.partition(cacheKeys, getBatchSize());
        List<T> cachedValues = partitions.stream()
            .map(this::findByCacheKey)
            .flatMap(Collection::stream)
            .toList();

        // 建立缓存键到值的映射
        Map<CacheKey, T> cacheKeyToValue = Maps.newHashMapWithExpectedSize(cacheKeys.size());
        for (int i = 0; i < cacheKeys.size() && i < cachedValues.size(); i++) {
            if (cachedValues.get(i) == null) {
                continue;
            }
            cacheKeyToValue.put(cacheKeys.get(i), cachedValues.get(i));
        }

        // 分离已缓存和未缓存的ID
        List<T> result = Lists.newArrayList();
        Set<K> missingIds = Sets.newHashSet();

        for (K id : ids) {
            CacheKey cacheKey = idToCacheKey.get(id);
            T value = cacheKeyToValue.get(cacheKey);

            if (value != null) {
                result.add(value);
            } else {
                missingIds.add(id);
            }
        }

        if (CollectionUtils.isEmpty(missingIds)) {
            return result;
        }

        // 加载未缓存的数据
        if (loader == null) {
            loader = this::findByIds;
        }
        Collection<T> loaded = loader.apply(missingIds);
        loaded.forEach(entity -> {
            setCache(entity); // 缓存新数据
            result.add(entity);
        });
        return result;
    }

    /**
     * 刷新缓存
     */
    default void refreshCache() {
        this.findAll().forEach(this::setCache);
    }

    /**
     * 清理缓存
     */
    default void clearCache() {
        this.findAll().forEach(this::deleteCache);
    }

    /**
     * 删除实体缓存
     */
    default void deleteCache(T model) {
        if (hasId(model)) {
            getCacheService().delete(getCacheKeyGenerator().key(model.getId()));
        }
    }

    /**
     * 删除实体缓存
     */
    default void deleteCache(K id) {
        if (id == null) {
            return;
        }
        getCacheService().delete(getCacheKeyGenerator().key(id));
    }

    /**
     * 设置实体缓存
     */
    default void setCache(T model) {
        if (hasId(model)) {
            getCacheService().set(getCacheKeyGenerator().key(model.getId()), model);
        }
    }

}
