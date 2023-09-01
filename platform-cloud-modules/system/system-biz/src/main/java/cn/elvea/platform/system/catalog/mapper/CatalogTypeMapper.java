package cn.elvea.platform.system.catalog.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
@Repository
public interface CatalogTypeMapper extends BaseEntityMapper<CatalogTypeEntity, Long> {
}
