package cc.elvea.platform.system.keyword.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.keyword.model.entity.KeywordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface KeywordRepository extends BaseEntityRepository<KeywordEntity, Long> {
}
