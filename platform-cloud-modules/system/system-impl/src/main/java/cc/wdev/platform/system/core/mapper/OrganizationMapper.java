package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.core.domain.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * OrganizationMapper
 *
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface OrganizationMapper extends BaseEntityMapper<OrganizationEntity, Long> {
}
