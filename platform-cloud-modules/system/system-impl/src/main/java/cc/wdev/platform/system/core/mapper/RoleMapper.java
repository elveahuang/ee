package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface RoleMapper extends BaseEntityMapper<RoleEntity, Long> {
}
