package cn.elvea.platform.system.security.api;

import cn.elvea.platform.system.security.model.dto.AuthorizationDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface AuthorizationApi {

    void save(AuthorizationDto saveDto);

    void deleteById(Long id);

    AuthorizationDto findById(Long state);

    AuthorizationDto findByState(String state);

    AuthorizationDto findByAuthorizationCodeValue(String code);

    AuthorizationDto findByOidcIdTokenValue(String token);

    AuthorizationDto findByAccessTokenValue(String token);

    AuthorizationDto findByRefreshTokenValue(String token);

}
