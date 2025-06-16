package cc.elvea.platform.system.dict.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.dict.model.entity.DictRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface DictRelationRepository extends BaseEntityRepository<DictRelationEntity, Long> {
}
