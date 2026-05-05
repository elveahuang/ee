package cc.wdev.dev.webapp.mybatis.repository;

import cc.wdev.dev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.platform.commons.data.mybatis.repository.BaseEntityRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MpUserRepository extends BaseEntityRepository<MpUserEntity, Long> {
}
