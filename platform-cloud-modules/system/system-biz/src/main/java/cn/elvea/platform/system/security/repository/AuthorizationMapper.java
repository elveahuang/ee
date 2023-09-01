package cn.elvea.platform.system.security.repository;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.security.model.entity.AuthorizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @see BaseEntityMapper
 * @since 0.0.1
 */
@Mapper
@Repository
public interface AuthorizationMapper extends BaseEntityMapper<AuthorizationEntity, Long> {
}
