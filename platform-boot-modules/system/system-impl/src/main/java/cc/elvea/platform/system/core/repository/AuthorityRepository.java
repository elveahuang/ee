package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface AuthorityRepository extends BaseEntityRepository<AuthorityEntity, Long> {
}
