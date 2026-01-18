package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.core.domain.entity.PositionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * BaseEntityMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface PositionMapper extends BaseEntityMapper<PositionEntity, Long> {
}
