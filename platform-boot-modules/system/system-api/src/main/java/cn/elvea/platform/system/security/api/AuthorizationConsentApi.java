package cn.elvea.platform.system.security.api;

import cn.elvea.platform.system.security.model.dto.AuthorizationConsentDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface AuthorizationConsentApi {

    void save(AuthorizationConsentDto saveDto);

    void deleteByKey(String clientId, String principalName);

    AuthorizationConsentDto findByKey(String clientId, String principalName);

}
