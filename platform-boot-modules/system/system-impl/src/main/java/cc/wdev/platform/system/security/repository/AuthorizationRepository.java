package cc.wdev.platform.system.security.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.security.domain.entity.AuthorizationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AuthorizationRepository extends BaseEntityRepository<AuthorizationEntity, Long> {
}
