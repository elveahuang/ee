package cn.elvea.platform.system.announcement.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface AnnouncementRepository extends BaseEntityRepository<AnnouncementEntity, Long> {
}
