package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 * @since 0.0.1
 */
@Mapper
@Repository
public interface RoleMapper extends BaseEntityMapper<RoleEntity, Long> {
}
