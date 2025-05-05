package cc.elvea.platform.system.attachment.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AttachmentTypeRepository extends BaseEntityRepository<AttachmentTypeEntity, Long> {
}
