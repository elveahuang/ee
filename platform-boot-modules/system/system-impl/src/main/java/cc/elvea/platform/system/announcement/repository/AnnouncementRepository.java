package cc.elvea.platform.system.announcement.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AnnouncementRepository extends BaseEntityRepository<AnnouncementEntity, Long> {
}
