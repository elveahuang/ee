package cc.elvea.platform.system.security.repository;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface AuthorizationMapper extends BaseEntityMapper<AuthorizationEntity, Long> {
}
