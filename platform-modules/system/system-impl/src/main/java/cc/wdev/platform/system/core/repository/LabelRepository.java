package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface LabelRepository extends BaseEntityRepository<LabelEntity, Long> {
}
