package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.AttachmentTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AttachmentTypeRepository extends BaseEntityRepository<AttachmentTypeEntity, Long> {
}
