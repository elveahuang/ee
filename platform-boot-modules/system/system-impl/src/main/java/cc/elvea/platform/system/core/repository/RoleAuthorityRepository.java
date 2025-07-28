package cc.elvea.platform.system.core.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.core.domain.entity.RoleAuthorityEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface RoleAuthorityRepository extends BaseEntityRepository<RoleAuthorityEntity, Long> {
}
