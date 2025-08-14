package top.baihu.platform.system.core.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.core.domain.entity.TagRelationEntity;

/**
 * @author elvea
 */
@Repository
public interface TagRelationRepository extends BaseEntityRepository<TagRelationEntity, Long> {
}
