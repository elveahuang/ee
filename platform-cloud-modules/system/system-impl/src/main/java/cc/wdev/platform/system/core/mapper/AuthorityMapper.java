package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.core.domain.entity.AuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AuthorityMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface AuthorityMapper extends BaseEntityMapper<AuthorityEntity, Long> {
}
