package cc.elvea.platform.system.commons.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.commons.model.entity.EntityLabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface EntityLabelRepository extends BaseEntityRepository<EntityLabelEntity, Long> {
}
