package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.DictItemEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface DictItemRepository extends BaseEntityRepository<DictItemEntity, Long> {
}
