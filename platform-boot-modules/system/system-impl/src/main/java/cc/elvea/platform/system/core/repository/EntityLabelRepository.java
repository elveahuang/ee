package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.EntityLabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface EntityLabelRepository extends BaseEntityRepository<EntityLabelEntity, Long> {
}
