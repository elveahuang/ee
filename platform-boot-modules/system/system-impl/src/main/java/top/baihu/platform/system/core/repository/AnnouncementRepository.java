package top.baihu.platform.system.core.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.core.domain.entity.AnnouncementEntity;

/**
 * @author elvea
 */
@Repository
public interface AnnouncementRepository extends BaseEntityRepository<AnnouncementEntity, Long> {
}
