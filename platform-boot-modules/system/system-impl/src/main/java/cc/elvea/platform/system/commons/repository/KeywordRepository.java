package cc.elvea.platform.system.commons.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.commons.model.entity.KeywordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface KeywordRepository extends BaseEntityRepository<KeywordEntity, Long> {
}
