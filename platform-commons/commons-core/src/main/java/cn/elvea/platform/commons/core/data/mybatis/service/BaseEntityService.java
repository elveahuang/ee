package cn.elvea.platform.commons.core.data.mybatis.service;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.commons.core.data.mybatis.utils.MyBatisPlusUtils;
import cn.elvea.platform.commons.core.service.AbstractService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.GenericsUtils;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @param <T> 实体
 * @param <K> 主键
 * @param <M> Mapper
 * @author elvea
 * @see EntityService
 * @see AbstractService
 * @since 0.0.1
 */
@Slf4j
@NoRepositoryBean
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseEntityService<T extends IdEntity, K extends Serializable, M extends BaseEntityMapper<T, K>>
        extends AbstractService implements EntityService<T, K> {

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

    public M getMapper() {
        return mapper;
    }

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
     * @see EntityService#findByIds(Collection)
     */
    @Override
    public List<T> findByIds(Collection<K> ids) {
        return (CollectionUtils.isNotEmpty(ids)) ? this.getMapper().selectBatchIds(ids) : Collections.emptyList();
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
        return this.getMapper().selectPage(
                MyBatisPlusUtils.getMyBatisPlusPage(pageable), Wrappers.emptyWrapper()
        ).getRecords();
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public Page<T> findByPage(Pageable pageable) {
        return MyBatisPlusUtils.toSpringDataPage(
                this.getMapper().selectPage(MyBatisPlusUtils.getMyBatisPlusPage(pageable), Wrappers.emptyWrapper())
        );
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
        SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.logger, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = tableInfo.getPropertyValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal) || com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    /**
     * @see EntityService#delete(IdEntity)
     */
    @Override
    public void delete(T entity) {
        this.getMapper().deleteById(entity);
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(K id) {
        this.getMapper().deleteById(id);
    }

    /**
     * @see EntityService#deleteBatch(Collection, int)
     */
    @Override
    public void deleteBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            this.getMapper().deleteBatchIds(entityList.stream().map(IdEntity::getId).collect(Collectors.toList()));
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
        return this.findById(id) != null;
    }

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
     * @return QueryWrapper
     */
    protected QueryChainWrapper<T> query() {
        return ChainWrappers.queryChain(getMapper());
    }

    /**
     * @return LambdaQueryChainWrapper
     */
    protected LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(getMapper()).setEntityClass(getEntityClass());
    }

    /**
     * @return UpdateWrapper
     */
    protected UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(getMapper());
    }

    /**
     * @return LambdaUpdateChainWrapper
     */
    protected LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(getMapper());
    }

}
