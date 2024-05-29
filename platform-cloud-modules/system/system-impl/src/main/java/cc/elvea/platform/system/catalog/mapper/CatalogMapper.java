package cc.elvea.platform.system.catalog.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.catalog.model.entity.CatalogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
@Repository
public interface CatalogMapper extends BaseEntityMapper<CatalogEntity, Long> {
}
