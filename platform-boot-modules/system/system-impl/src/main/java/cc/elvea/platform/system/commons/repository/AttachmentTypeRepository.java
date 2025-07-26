package cc.elvea.platform.system.commons.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.commons.model.entity.AttachmentTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AttachmentTypeRepository extends BaseEntityRepository<AttachmentTypeEntity, Long> {
}
