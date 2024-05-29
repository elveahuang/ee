package cc.elvea.platform.system.security.api;

import cc.elvea.platform.system.security.model.dto.AuthorizationConsentDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AuthorizationConsentApi {

    void save(AuthorizationConsentDto saveDto);

    void deleteByKey(String clientId, String principalName);

    AuthorizationConsentDto findByKey(String clientId, String principalName);

}
