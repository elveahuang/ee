package cn.elvea.platform.commons.core.data.jpa.service;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.commons.core.service.AbstractService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @param <T> 实体
 * @param <K> 主键
 * @param <R> Repository
 * @author elvea
 * @see AbstractService
 * @see EntityService
 * @since 0.0.1
 */
@Slf4j
@NoRepositoryBean
public abstract class BaseEntityService<T extends IdEntity, K extends Serializable, R extends BaseEntityRepository<T, K>>
        extends AbstractService implements EntityService<T, K> {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected R repository;

    protected Class<T> entityClass = currentEntityClass();

    protected Class<K> entityIdClass = currentEntityIdClass();

    // -----------------------------------------------------------------------------------------------------------------
    // 辅助方法
    // -----------------------------------------------------------------------------------------------------------------

    public R getRepository() {
        return repository;
    }

    /**
     * @see EntityService#getEntityClass()
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @see EntityService#getEntityIdClass()
     */
    public Class<K> getEntityIdClass() {
        return entityIdClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EntityService
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see EntityService#findById(Serializable)
     */
    @Override
    public T findById(K id) {
        return this.getRepository().findById(id).orElse(null);
    }

    /**
     * @see EntityService#findByIds(Collection)
     */
    @Override
    public List<T> findByIds(Collection<K> ids) {
        return (CollectionUtils.isNotEmpty(ids)) ?
                CollectionUtils.toArrayList(getRepository().findAllById(ids)) : Collections.emptyList();
    }

    /**
     * @see EntityService#findAll()
     */
    @Override
    public List<T> findAll() {
        return CollectionUtils.toArrayList(this.getRepository().findAll());
    }

    /**
     * @see EntityService#findAll(Sort)
     */
    @Override
    public List<T> findAll(Sort sort) {
        return CollectionUtils.toArrayList(this.getRepository().findAll(sort));
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public List<T> findAll(Pageable pageable) {
        return CollectionUtils.toArrayList(this.getRepository().findAll(pageable));
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public Page<T> findByPage(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    /**
     * @see EntityService#insert(IdEntity)
     */
    @Override
    public T insert(T entity) {
        return this.save(entity);
    }

    /**
     * @see EntityService#insertBatch(Collection, int)
     */
    @Override
    public void insertBatch(Collection<T> entityList, int batchSize) {
        this.getRepository().saveAll(entityList);
    }

    /**
     * @see EntityService#updateById(IdEntity)
     */
    @Override
    public T updateById(T entity) {
        return this.save(entity);
    }

    /**
     * @see EntityService#updateBatchById(Collection, int)
     */
    @Override
    public void updateBatchById(Collection<T> entityList, int batchSize) {
        this.getRepository().saveAll(entityList);
    }

    /**
     * @see EntityService#save(IdEntity)
     */
    @Override
    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    /**
     * @see EntityService#saveBatch(Collection, int)
     */
    @Override
    public void saveBatch(Collection<T> entityList, int batchSize) {
        CollectionUtils.toArrayList(this.getRepository().saveAll(entityList));
    }

    /**
     * @see EntityService#delete(IdEntity)
     */
    @Override
    public void delete(T entity) {
        this.getRepository().delete(entity);
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(K id) {
        this.getRepository().deleteById(id);
    }

    /**
     * @see EntityService#deleteBatch(Collection, int)
     */
    @Override
    public void deleteBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            this.getRepository().deleteAll(entityList);
        }
    }

    /**
     * @see EntityService#deleteAll()
     */
    @Override
    public void deleteAll() {
        this.getRepository().deleteAll();
    }

    /**
     * @see EntityService#count()
     */
    @Override
    public long count() {
        return this.getRepository().count();
    }

    /**
     * @see EntityService#existsById(Serializable)
     */
    @Override
    public boolean existsById(K id) {
        return this.getRepository().existsById(id);
    }


}
