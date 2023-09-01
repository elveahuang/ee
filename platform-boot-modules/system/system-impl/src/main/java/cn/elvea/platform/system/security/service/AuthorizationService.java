package cn.elvea.platform.system.security.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.security.model.entity.AuthorizationEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 0.0.1
 */
public interface AuthorizationService extends CachingEntityService<AuthorizationEntity, Long> {

    AuthorizationEntity findByState(String state);

    AuthorizationEntity findByAuthorizationCodeValue(String code);

    AuthorizationEntity findByOidcIdTokenValue(String token);

    AuthorizationEntity findByAccessTokenValue(String token);

    AuthorizationEntity findByRefreshTokenValue(String token);

}
