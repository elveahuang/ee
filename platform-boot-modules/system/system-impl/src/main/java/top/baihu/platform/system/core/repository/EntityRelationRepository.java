package top.baihu.platform.system.core.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.core.domain.entity.EntityRelationEntity;

/**
 * @author elvea
 */
@Repository
public interface EntityRelationRepository extends BaseEntityRepository<EntityRelationEntity, Long> {
}
