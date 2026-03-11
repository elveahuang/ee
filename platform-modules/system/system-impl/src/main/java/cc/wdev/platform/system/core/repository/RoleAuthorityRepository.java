package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.RoleAuthorityEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface RoleAuthorityRepository extends BaseEntityRepository<RoleAuthorityEntity, Long> {
}
