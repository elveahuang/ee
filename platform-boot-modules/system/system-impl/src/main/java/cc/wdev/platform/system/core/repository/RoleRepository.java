package cc.wdev.platform.system.core.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface RoleRepository extends BaseEntityRepository<RoleEntity, Long> {
}
