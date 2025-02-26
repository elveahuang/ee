package cc.elvea.platform.system.catalog.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface CatalogTypeMapper extends BaseEntityMapper<CatalogTypeEntity, Long> {
}
