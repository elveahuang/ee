package cc.elvea.platform.system.security.api;

import cc.elvea.platform.system.security.model.dto.AuthorizationConsentDto;

/**
 * @author elvea
 */
public interface AuthorizationConsentApi {

    void save(AuthorizationConsentDto saveDto);

    void deleteByKey(String clientId, String principalName);

    AuthorizationConsentDto findByKey(String clientId, String principalName);

}
