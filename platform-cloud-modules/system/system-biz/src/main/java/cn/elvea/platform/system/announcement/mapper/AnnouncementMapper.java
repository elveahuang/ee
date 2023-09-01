package cn.elvea.platform.system.announcement.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
@Repository
public interface AnnouncementMapper extends BaseEntityMapper<AnnouncementEntity, Long> {
}
