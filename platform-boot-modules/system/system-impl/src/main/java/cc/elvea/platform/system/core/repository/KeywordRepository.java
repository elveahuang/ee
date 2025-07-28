package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.KeywordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface KeywordRepository extends BaseEntityRepository<KeywordEntity, Long> {
}
