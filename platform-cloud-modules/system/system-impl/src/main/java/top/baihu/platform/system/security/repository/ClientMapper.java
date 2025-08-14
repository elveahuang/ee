package top.baihu.platform.system.security.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import top.baihu.platform.system.security.domain.entity.ClientEntity;

/**
 * @author elvea
 * @see BaseEntityMapper
 */
@Mapper
@Repository
public interface ClientMapper extends BaseEntityMapper<ClientEntity, Long> {
}
