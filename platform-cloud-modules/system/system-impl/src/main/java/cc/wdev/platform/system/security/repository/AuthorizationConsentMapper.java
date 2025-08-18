package cc.wdev.platform.system.security.repository;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.security.domain.entity.AuthorizationConsentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface AuthorizationConsentMapper extends BaseEntityMapper<AuthorizationConsentEntity, Long> {
}
