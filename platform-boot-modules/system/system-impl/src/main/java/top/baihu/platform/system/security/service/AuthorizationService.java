package top.baihu.platform.system.security.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.security.domain.entity.AuthorizationEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AuthorizationService extends CachingEntityService<AuthorizationEntity, Long> {

    void updateByUuid(AuthorizationEntity entity);

    void deleteByUuid(String uuid);

    AuthorizationEntity findByUuid(String uuid);

    AuthorizationEntity findByState(String state);

    AuthorizationEntity findByAuthorizationCodeValue(String code);

    AuthorizationEntity findByOidcIdTokenValue(String token);

    AuthorizationEntity findByAccessTokenValue(String token);

    AuthorizationEntity findByRefreshTokenValue(String token);

}
