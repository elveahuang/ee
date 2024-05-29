package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.PositionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * BaseEntityMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 * @since 24.1.0
 */
@Mapper
@Repository
public interface PositionMapper extends BaseEntityMapper<PositionEntity, Long> {
}
