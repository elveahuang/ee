package cc.wdev.platform.system.security.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.security.domain.entity.AuthorizationConsentEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AuthorizationConsentRepository extends BaseEntityRepository<AuthorizationConsentEntity, Long> {
}
