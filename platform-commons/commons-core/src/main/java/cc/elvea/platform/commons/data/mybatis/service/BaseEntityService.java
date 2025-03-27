package cc.elvea.platform.commons.data.mybatis.service;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.mybatis.domain.BaseEntity;
import cc.elvea.platform.commons.data.mybatis.domain.SimpleEntity;
import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.commons.data.mybatis.utils.MyBatisPlusUtils;
import cc.elvea.platform.commons.service.AbstractService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.GenericsUtils;
import cc.elvea.platform.commons.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static cc.elvea.platform.commons.data.mybatis.utils.MyBatisPlusUtils.getMyBatisPlusPage;

/**
 * @param <T> 实体
 * @param <K> 主键
 * @param <M> Mapper
 * @author elvea
 * @see AbstractService
 * @see EnhancedEntityService
 * @see EntityService
 */
@Slf4j
@Transactional
@NoRepositoryBean
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseEntityService<T extends IdEntity, K extends Serializable, M extends BaseEntityMapper<T, K>>
        extends AbstractService
        implements EntityService<T, K>, EnhancedEntityService<T, K, M> {

    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    protected M mapper;

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;

    protected Class<T> entityClass = currentEntityClass();

    protected Class<K> entityIdClass = currentEntityIdClass();

    protected Class<M> mapperClass = currentMapperClass();

    // -----------------------------------------------------------------------------------------------------------------
    // 辅助方法
    // -----------------------------------------------------------------------------------------------------------------

    protected Class<M> currentMapperClass() {
        return GenericsUtils.getSuperGenericType(getClass(), BaseEntityService.class, 2);
    }

    /**
     * @see EnhancedEntityService#getMapper()
     */
    @Override
    public M getMapper() {
        return mapper;
    }

    /**
     * @see EnhancedEntityService#getMapperClass()
     */
    @Override
    public Class<M> getMapperClass() {
        return mapperClass;
    }

    /**
     * @see EntityService#getEntityClass()
     */
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @see EntityService#getEntityIdClass()
     */
    @Override
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
        return this.getMapper().selectById(id);
    }

    /**
     * @see EntityService#findById(Serializable, Consumer)
     */
    @Override
    public T findById(K id, Consumer<T> extraFn) {
        T entity = this.getMapper().selectById(id);
        extraFn.accept(entity);
        return entity;
    }

    /**
     * @see EntityService#findByIds(Collection)
     */
    @Override
    public List<T> findByIds(Collection<K> ids) {
        return (CollectionUtils.isNotEmpty(ids)) ?
                this.getMapper().selectByIds(ids) : Collections.emptyList();
    }

    /**
     * @see EntityService#findAll()
     */
    @Override
    public List<T> findAll() {
        return getMapper().selectList(Wrappers.emptyWrapper());
    }

    /**
     * @see EntityService#findAll(Sort)
     */
    @Override
    public List<T> findAll(Sort sort) {
        return getMapper().selectList(Wrappers.emptyWrapper());
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public List<T> findAll(Pageable pageable) {
        return this.getMapper().selectPage(getMyBatisPlusPage(pageable), Wrappers.emptyWrapper()).getRecords();
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public Page<T> findByPage(Pageable pageable) {
        return MyBatisPlusUtils.toSpringDataPage(this.getMapper().selectPage(getMyBatisPlusPage(pageable), Wrappers.emptyWrapper()));
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public Page<T> findByPage(Pageable pageable, T example) {
        if (example == null) {
            return findByPage(pageable);
        }
        return MyBatisPlusUtils.toSpringDataPage(this.getMapper().selectPage(getMyBatisPlusPage(pageable), new QueryWrapper<>(example)));
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
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
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
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    /**
     * @see EntityService#save(IdEntity)
     */
    @Override
    public T save(T entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            getMapper().insert(entity);
        } else {
            getMapper().updateById(entity);
        }
        return entity;
    }

    /**
     * @see EntityService#saveBatch(Collection, int)
     */
    @Override
    public void saveBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        SqlHelper.saveOrUpdateBatch(this.sqlSessionFactory, this.mapperClass, this.logger, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = tableInfo.getPropertyValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal) || com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(K id) {
        this.getMapper().deleteById(id);
    }

    /**
     * @see EntityService#deleteBatchById(Collection)
     */
    @Override
    public void deleteBatchById(Collection<K> entityIdList) {
        if (CollectionUtils.isNotEmpty(entityIdList)) {
            this.getMapper().deleteByIds(entityIdList);
        }
    }

    /**
     * @see EntityService#delete(IdEntity)
     */
    @Override
    public void delete(T entity) {
        this.getMapper().deleteById(entity);
    }

    /**
     * @see EntityService#deleteBatch(Collection, int)
     */
    @Override
    public void deleteBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            this.getMapper().deleteByIds(entityList.stream().map(IdEntity::getId).collect(Collectors.toList()));
        }
    }

    /**
     * @see EntityService#deleteAll()
     */
    @Override
    public void deleteAll() {
        this.getMapper().delete(Wrappers.emptyWrapper());
    }

    /**
     * @see EntityService#softDelete(IdEntity)
     */
    @Override
    public void softDelete(T entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setActive(Boolean.FALSE);
            baseEntity.setDeletedAt(getCurLocalDateTime());
            baseEntity.setDeletedBy(SecurityUtils.getUid());
            this.save(entity);
        } else if (entity instanceof SimpleEntity simpleEntity) {
            simpleEntity.setActive(Boolean.FALSE);
            this.save(entity);
        }
    }

    /**
     * @see EntityService#softDeleteBatch(Collection, int)
     */
    @Override
    public void softDeleteBatch(Collection<T> entityList, int batchSize) {
        this.updateBatchById(entityList.stream().peek(e -> {
            if (e instanceof BaseEntity entity) {
                entity.setActive(Boolean.FALSE);
                entity.setDeletedAt(getCurLocalDateTime());
                entity.setDeletedBy(SecurityUtils.getUid());
            } else if (e instanceof SimpleEntity entity) {
                entity.setActive(Boolean.FALSE);
            }
        }).toList(), batchSize);
    }

    /**
     * @see EntityService#softDeleteAll()
     */
    @Override
    public void softDeleteAll() {
    }

    /**
     * @see EntityService#count()
     */
    @Override
    public long count() {
        return this.getMapper().selectCount(Wrappers.emptyWrapper());
    }

    /**
     * @see EntityService#existsById(Serializable)
     */
    @Override
    public boolean existsById(K id) {
        return null != id && this.findById(id) != null;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EnhancedEntityService
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @see EnhancedEntityService#findOneByExample(IdEntity)
     */
    @Override
    public T findOneByExample(T example) {
        return findOneByWrapper(new QueryWrapper<>(example));
    }

    /**
     * @see EnhancedEntityService#findOneByWrapper(QueryWrapper)
     */
    @Override
    public T findOneByWrapper(QueryWrapper<T> wrapper) {
        IPage<T> page = this.mapper.selectPage(MyBatisPlusUtils.getLimitPage(), wrapper);
        if (page != null && CollectionUtils.isNotEmpty(page.getRecords())) {
            return page.getRecords().getFirst();
        }
        return null;
    }

    /**
     * @see EnhancedEntityService#findAllByMpPage(IPage)
     */
    @Override
    public List<T> findAllByMpPage(IPage<T> page) {
        return this.mapper.selectList(page, Wrappers.emptyWrapper());
    }

    /**
     * @see EnhancedEntityService#findByMpPage(IPage)
     */
    @Override
    public IPage<T> findByMpPage(IPage<T> page) {
        return this.findByMpPage(page, Wrappers.emptyWrapper());
    }

    /**
     * @see EnhancedEntityService#findByMpPage(IPage, QueryWrapper)
     */
    @Override
    public IPage<T> findByMpPage(IPage<T> page, QueryWrapper<T> wrapper) {
        return this.mapper.selectPage(page, wrapper);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 辅助方法
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 获取实体执行方法
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(getMapperClass(), sqlMethod);
    }

    /**
     * 执行批量操作
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.sqlSessionFactory, this.logger, list, batchSize, consumer);
    }

    /**
     * 执行批量操作
     */
    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return executeBatch(list, DEFAULT_BATCH_SIZE, consumer);
    }

    /**
     * @return QueryChainWrapper
     */
    public QueryChainWrapper<T> queryWrapper() {
        return ChainWrappers.queryChain(getMapper());
    }

    /**
     * @return LambdaQueryChainWrapper
     */
    protected LambdaQueryChainWrapper<T> lambdaQueryWrapper() {
        return ChainWrappers.lambdaQueryChain(getMapper()).setEntityClass(getEntityClass());
    }

    /**
     * @return UpdateChainWrapper
     */
    protected UpdateChainWrapper<T> updateWrapper() {
        return ChainWrappers.updateChain(getMapper());
    }

    /**
     * @return LambdaUpdateChainWrapper
     */
    protected LambdaUpdateChainWrapper<T> lambdaUpdateWrapper() {
        return ChainWrappers.lambdaUpdateChain(getMapper());
    }

}
