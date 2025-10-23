package cc.wdev.webapp.mybatis.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.webapp.mybatis.domain.entity.MpUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MpUserMapper extends BaseEntityMapper<MpUserEntity, Long> {
}
