package cc.elvea.platform.commons.service;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.utils.GenericsUtils;
import cc.elvea.platform.commons.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/**
 * 通用实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see Service
 */
public interface EntityService<T extends IdEntity, K extends Serializable> extends Service {

    int DEFAULT_BATCH_SIZE = 1000;

    Class<T> getEntityClass();

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
     */
    T findById(K id);

    /**
     * 根据ID查询唯一记录，并执行回调(一般用于获取附加信息)
     */
    T findById(K id, Consumer<T> extraFn);

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
     */
    List<T> findAll(Sort sort);

    /**
     * 查询所有记录，支持分页
     */
    List<T> findAll(Pageable pageable);

    /**
     * 查询所有记录，支持分页
     */
    Page<T> findByPage(Pageable pageable);

    /**
     * 查询所有记录，支持分页
     */
    Page<T> findByPage(Pageable pageable, T example);

    /**
     * 新增实体
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
    void deleteById(K id);

    /**
     * 删除多个实体
     */
    void deleteBatchById(Collection<K> entityIdList);

    /**
     * 删除单个实体
     */
    void delete(T entity);

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
     * 删除单个实体
     */
    default void softDeleteById(K id) {
        this.softDelete(this.findById(id));
    }

    /**
     * 删除单个实体(执行回调方法，一般用于删除关联关系)
     */
    default void softDeleteById(K id, Consumer<T> callback) {
        T entity = this.findById(id);
        this.softDelete(entity, callback);
    }

    /**
     * 删除多个实体
     */
    default void softDeleteBatchById(Collection<K> entityIdList) {
        this.softDeleteBatch(this.findByIds(entityIdList));
    }

    /**
     * 删除单个实体
     */
    default void softDelete(T entity) {
    }

    /**
     * 删除单个实体(执行回调方法，一般用于删除关联关系)
     */
    default void softDelete(T entity, Consumer<T> callback) {
        this.softDelete(entity);
        callback.accept(entity);
    }

    /**
     * 删除多个实体
     */
    default void softDeleteBatch(Collection<T> entityList) {
        this.softDeleteBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 删除多个实体
     */
    default void softDeleteBatch(Collection<T> entityList, int batchSize) {
    }

    /**
     * 删除全部实体
     */
    default void softDeleteAll() {
    }

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
