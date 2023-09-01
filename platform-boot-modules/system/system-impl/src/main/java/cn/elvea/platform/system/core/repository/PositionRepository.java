package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.core.model.entity.PositionEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface PositionRepository extends BaseEntityRepository<PositionEntity, Long> {
}
