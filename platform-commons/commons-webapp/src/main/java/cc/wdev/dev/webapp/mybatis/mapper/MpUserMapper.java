package cc.wdev.dev.webapp.mybatis.mapper;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MpUserMapper extends BaseEntityMapper<MpUserEntity, Long> {
}
