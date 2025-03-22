package cc.elvea.platform.system.security.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AuthorizationService extends CachingEntityService<AuthorizationEntity, Long> {

    AuthorizationEntity findByState(String state);

    AuthorizationEntity findByAuthorizationCodeValue(String code);

    AuthorizationEntity findByOidcIdTokenValue(String token);

    AuthorizationEntity findByAccessTokenValue(String token);

    AuthorizationEntity findByRefreshTokenValue(String token);

}
