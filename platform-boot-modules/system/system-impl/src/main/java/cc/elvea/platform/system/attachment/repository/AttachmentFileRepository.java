package cc.elvea.platform.system.attachment.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.attachment.model.entity.AttachmentFileEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AttachmentFileRepository extends BaseEntityRepository<AttachmentFileEntity, Long> {
}
