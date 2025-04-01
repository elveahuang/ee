package cc.elvea.platform.commons.data.jpa.service;

import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @see BaseEntityService
 */
@Slf4j
@NoRepositoryBean
@Transactional
public abstract class BaseCachingEntityService<T extends IdEntity, K extends Serializable, R extends BaseEntityRepository<T, K>>
        extends BaseEntityService<T, K, R>
        implements CachingEntityService<T, K> {

    private CacheService cacheService;

    /**
     * @see EntityService#save(IdEntity)
     */
    @Override
    public T save(T entity) {
        T t = super.save(entity);
        deleteCache(t);
        return t;
    }

    /**
     * @see EntityService#insert(IdEntity)
     */
    @Override
    public T insert(T entity) {
        T t = super.insert(entity);
        setCache(t);
        return t;
    }

    /**
     * @see EntityService#updateById(IdEntity)
     */
    @Override
    public T updateById(T entity) {
        T t = super.updateById(entity);
        deleteCache(t);
        return t;
    }

    /**
     * @see EntityService#saveBatch(Collection, int)
     */
    @Override
    public void saveBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            super.saveBatch(entityList, batchSize);
            entityList.forEach(this::deleteCache);
        }
    }

    /**
     * @see EntityService#delete(IdEntity)
     */
    @Override
    public void delete(T entity) {
        super.delete(entity);
        this.deleteCache(entity);
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(K id) {
        this.delete(findById(id));
    }

    /**
     * @see EntityService#deleteBatch(Collection, int)
     */
    @Override
    public void deleteBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            super.deleteBatch(entityList);
            entityList.forEach(this::deleteCache);
        }
    }

    /**
     * @see EntityService#deleteAll()
     */
    @Override
    public void deleteAll() {
        super.deleteAll();
        this.clearCache();
    }

    /**
     * @see CachingEntityService#getCacheService()
     */
    @Override
    public CacheService getCacheService() {
        return this.cacheService;
    }

    @Autowired
    @Qualifier("cacheService")
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

}
