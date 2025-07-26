package cc.elvea.platform.system.security.manager.impl;

import cc.elvea.platform.system.security.manager.AuthorizationConsentManager;
import cc.elvea.platform.system.security.model.dto.AuthorizationConsentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class AuthorizationConsentManagerImpl implements AuthorizationConsentManager {

    /**
     * @see AuthorizationConsentManager#save(AuthorizationConsentDto)
     */
    @Override
    public void save(AuthorizationConsentDto saveDto) {
    }

    /**
     * @see AuthorizationConsentManager#deleteByKey(String, String)
     */
    @Override
    public void deleteByKey(String clientId, String principalName) {
    }

    /**
     * @see AuthorizationConsentManager#findByKey(String, String)
     */
    @Override
    public AuthorizationConsentDto findByKey(String clientId, String principalName) {
        return null;
    }

}
