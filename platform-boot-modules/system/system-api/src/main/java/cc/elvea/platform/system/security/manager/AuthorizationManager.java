package cc.elvea.platform.system.security.manager;

import cc.elvea.platform.system.security.domain.dto.AuthorizationDto;

/**
 * @author elvea
 */
public interface AuthorizationManager {

    void save(AuthorizationDto saveDto);

    void deleteById(Long id);

    void deleteByUuid(String uuid);

    AuthorizationDto findById(Long id);

    AuthorizationDto findByUuid(String uuid);

    AuthorizationDto findByState(String state);

    AuthorizationDto findByAuthorizationCodeValue(String code);

    AuthorizationDto findByOidcIdTokenValue(String token);

    AuthorizationDto findByAccessTokenValue(String token);

    AuthorizationDto findByRefreshTokenValue(String token);

}
