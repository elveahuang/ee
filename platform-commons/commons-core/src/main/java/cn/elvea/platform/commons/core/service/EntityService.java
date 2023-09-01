package cn.elvea.platform.commons.core.service;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.utils.GenericsUtils;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/**
 * 通用实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see Service
 * @since 0.0.1
 */
public interface EntityService<T extends IdEntity, K extends Serializable> extends Service {

    /**
     *
     */
    int DEFAULT_BATCH_SIZE = 1000;

    /**
     *
     */
    Class<T> getEntityClass();

    /**
     *
     */
    Class<K> getEntityIdClass();

    /**
     * 获取当前实体类
     */
    default Class<T> currentEntityClass() {
        return GenericsUtils.getSuperGenericType(getClass(), EntityService.class, 0);
    }

    /**
     * 获取当前实体主键类
     */
    default Class<K> currentEntityIdClass() {
        return GenericsUtils.getSuperGenericType(getClass(), EntityService.class, 1);
    }

    /**
     * 根据ID查询唯一记录
     *
     * @param id ID
     * @return T
     */
    T findById(K id);

    /**
     * 根据ID查询多条记录
     */
    List<T> findByIds(Collection<K> ids);

    /**
     * 查询所有记录
     */
    List<T> findAll();

    /**
     * 查询所有记录，支持排序
     *
     * @return Iterable<T>
     */
    List<T> findAll(Sort sort);

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    List<T> findAll(Pageable pageable);

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    Page<T> findByPage(Pageable pageable);

    /**
     * 新增实体
     *
     * @return T
     */
    T insert(T entity);

    /**
     * 批量新增实体
     */
    default void insertBatch(Collection<T> entityList) {
        this.insertBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量新增实体
     */
    void insertBatch(Collection<T> entityList, int batchSize);

    /**
     * 更新实体
     *
     * @return T
     */
    T updateById(T entity);

    /**
     * 批量更新实体
     */
    default void updateBatchById(Collection<T> entityList) {
        this.updateBatchById(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量更新实体
     */
    void updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * 保存实体
     *
     * @return T
     */
    T save(T entity);

    /**
     * 批量保存实体
     */
    default void saveBatch(Collection<T> entityList) {
        this.saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量保存实体
     */
    void saveBatch(Collection<T> entityList, int batchSize);

    /**
     * 删除单个实体
     */
    void delete(T entity);

    /**
     * 删除单个实体
     */
    void deleteById(K id);

    /**
     * 删除多个实体
     */
    default void deleteBatch(Collection<T> entityList) {
        this.deleteBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 删除多个实体
     */
    void deleteBatch(Collection<T> entityList, int batchSize);

    /**
     * 删除全部实体
     */
    void deleteAll();

    /**
     * 查询记录总数
     */
    long count();

    /**
     * 是否存在指定ID的记录
     */
    boolean existsById(K id);

    /**
     * 实体是否存在主键值
     */
    default boolean hasId(final Object object) {
        if (!ObjectUtils.isEmpty(object) && object instanceof IdEntity entity) {
            return !isEmpty(entity.getId()) && entity.getId() > 0;
        }
        return false;
    }

}
