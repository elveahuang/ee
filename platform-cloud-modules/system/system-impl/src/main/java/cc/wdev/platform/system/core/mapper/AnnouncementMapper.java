package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.wdev.platform.system.core.domain.entity.AnnouncementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface AnnouncementMapper extends BaseEntityMapper<AnnouncementEntity, Long> {
}
