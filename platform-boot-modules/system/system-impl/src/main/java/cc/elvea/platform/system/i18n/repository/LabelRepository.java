package cc.elvea.platform.system.i18n.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.i18n.model.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface LabelRepository extends BaseEntityRepository<LabelEntity, Long> {
}
