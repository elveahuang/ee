package cc.elvea.platform.system.attachment.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface AttachmentTypeRepository extends BaseEntityRepository<AttachmentTypeEntity, Long> {
}
