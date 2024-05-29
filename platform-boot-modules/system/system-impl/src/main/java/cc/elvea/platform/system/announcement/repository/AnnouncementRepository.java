package cc.elvea.platform.system.announcement.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface AnnouncementRepository extends BaseEntityRepository<AnnouncementEntity, Long> {
}
