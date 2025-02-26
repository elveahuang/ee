package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserSessionMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface UserSessionMapper extends BaseEntityMapper<UserSessionEntity, Long> {
}
