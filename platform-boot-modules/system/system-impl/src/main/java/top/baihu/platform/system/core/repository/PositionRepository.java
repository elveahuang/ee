package top.baihu.platform.system.core.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.core.domain.entity.PositionEntity;

/**
 * @author elvea
 */
@Repository
public interface PositionRepository extends BaseEntityRepository<PositionEntity, Long> {
}
