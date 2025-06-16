package cc.elvea.platform.system.security.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.security.model.entity.AuthorizationConsentEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AuthorizationConsentRepository extends BaseEntityRepository<AuthorizationConsentEntity, Long> {
}
