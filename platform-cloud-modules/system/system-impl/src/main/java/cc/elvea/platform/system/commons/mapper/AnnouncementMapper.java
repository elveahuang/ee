package cc.elvea.platform.system.commons.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.commons.model.entity.AnnouncementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface AnnouncementMapper extends BaseEntityMapper<AnnouncementEntity, Long> {
}
