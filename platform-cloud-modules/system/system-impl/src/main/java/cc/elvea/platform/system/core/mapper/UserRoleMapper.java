package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserRoleMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 * @since 24.1.0
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseEntityMapper<UserRoleEntity, Long> {
}
