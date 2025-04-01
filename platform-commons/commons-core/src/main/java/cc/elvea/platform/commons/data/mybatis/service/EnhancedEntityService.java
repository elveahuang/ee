package cc.elvea.platform.commons.data.mybatis.service;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.commons.service.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 增强实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see EntityService
 * @see Service
 */
public interface EnhancedEntityService<T extends IdEntity, K extends Serializable, M extends BaseEntityMapper<T, K>>
        extends EntityService<T, K> {

    M getMapper();

    Class<M> getMapperClass();

    /**
     * 查询单条记录
     */
    T findOneByExample(T example);

    /**
     * 查询单条记录
     */
    T findOneByWrapper(QueryWrapper<T> wrapper);

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    List<T> findAllByMpPage(IPage<T> page);

    /**
     * 查询所有记录，支持分页
     *
     * @return IPage<T>
     */
    IPage<T> findByMpPage(IPage<T> page);

    /**
     * 查询所有记录，支持分页
     *
     * @return IPage<T>
     */
    IPage<T> findByMpPage(IPage<T> page, QueryWrapper<T> wrapper);

}
