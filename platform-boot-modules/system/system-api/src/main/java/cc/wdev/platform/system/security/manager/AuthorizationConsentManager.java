package cc.wdev.platform.system.security.manager;

import cc.wdev.platform.system.security.domain.dto.AuthorizationConsentDto;

/**
 * @author elvea
 */
public interface AuthorizationConsentManager {

    void save(AuthorizationConsentDto saveDto);

    void deleteByKey(String clientId, String principalName);

    AuthorizationConsentDto findByKey(String clientId, String principalName);

}
