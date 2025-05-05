package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ConfigMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface ConfigMapper extends BaseEntityMapper<ConfigEntity, Long> {
}
