package cc.elvea.platform.system.dict.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.dict.model.entity.DictItemEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface DictItemRepository extends BaseEntityRepository<DictItemEntity, Long> {
}
