package cn.elvea.platform.system.security.api.impl;

import cn.elvea.platform.system.security.api.AuthorizationConsentApi;
import cn.elvea.platform.system.security.model.dto.AuthorizationConsentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class AuthorizationConsentApiImpl implements AuthorizationConsentApi {

    /**
     * @see AuthorizationConsentApi#save(AuthorizationConsentDto)
     */
    @Override
    public void save(AuthorizationConsentDto saveDto) {
    }

    /**
     * @see AuthorizationConsentApi#deleteByKey(String, String)
     */
    @Override
    public void deleteByKey(String clientId, String principalName) {
    }

    /**
     * @see AuthorizationConsentApi#findByKey(String, String)
     */
    @Override
    public AuthorizationConsentDto findByKey(String clientId, String principalName) {
        return null;
    }

}
