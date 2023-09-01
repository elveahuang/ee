package cn.elvea.platform.commons.core.service;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.utils.SpringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.elvea.platform.commons.core.constants.GlobalConstants.MAX_BATCH_CACHE_KEY_SIZE;

/**
 * 通用实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see EntityService
 * @see Service
 * @since 0.0.1
 */
public interface CachingEntityService<T extends IdEntity, K extends Serializable> extends EntityService<T, K> {

    default CacheService getCacheService() {
        return SpringUtils.getBean(CacheService.class);
    }

    default int getBatchSize() {
        return MAX_BATCH_CACHE_KEY_SIZE;
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
        CacheKey cacheKey = getCacheKeyGenerator().key(id);
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
        List<CacheKey> cacheKeyList = ids.stream().map(getCacheKeyGenerator()::key).collect(Collectors.toList());
        List<List<CacheKey>> partitionKeyList = Lists.partition(cacheKeyList, getBatchSize());
        List<T> valueList = partitionKeyList.stream().map(this::findByCacheKey).flatMap(Collection::stream).toList();

        List<K> keyList = Lists.newArrayList(ids);
        Set<K> missedKeys = Sets.newLinkedHashSet();

        List<T> entityList = new ArrayList<>();
        for (int i = 0; i < valueList.size(); i++) {
            T v = valueList.get(i);
            K k = keyList.get(i);
            if (v == null) {
                missedKeys.add(k);
            } else {
                entityList.add(v);
            }
        }

        if (CollectionUtils.isNotEmpty(missedKeys)) {
            if (loader == null) {
                loader = this::findByIds;
            }
            Collection<T> missList = loader.apply(missedKeys);
            missList.forEach(this::setCache);
            entityList.addAll(missList);
        }
        return entityList;
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
     * 设置实体缓存
     */
    default void setCache(T model) {
        if (hasId(model)) {
            getCacheService().set(getCacheKeyGenerator().key(model.getId()), model);
        }
    }

}
