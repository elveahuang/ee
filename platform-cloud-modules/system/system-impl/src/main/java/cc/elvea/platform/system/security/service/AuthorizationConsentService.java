package cc.elvea.platform.system.security.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.security.model.entity.AuthorizationConsentEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AuthorizationConsentService extends CachingEntityService<AuthorizationConsentEntity, Long> {

    AuthorizationConsentEntity findByKey(String clientId, String principalName);

    void deleteByKey(String clientId, String principalName);

}
