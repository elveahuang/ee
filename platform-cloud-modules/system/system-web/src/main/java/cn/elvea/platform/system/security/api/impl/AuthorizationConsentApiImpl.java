package cn.elvea.platform.system.security.api.impl;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.security.api.AuthorizationConsentApi;
import cn.elvea.platform.system.security.model.dto.AuthorizationConsentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@AllArgsConstructor
@RestController
public class AuthorizationConsentApiImpl implements AuthorizationConsentApi {

    /**
     * @see AuthorizationConsentApi#save(AuthorizationConsentDto)
     */
    @Override
    public R<Boolean> save(AuthorizationConsentDto saveDto) {
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationConsentApi#deleteByKey(String, String)
     */
    @Override
    public R<Boolean> deleteByKey(String clientId, String principalName) {
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationConsentApi#findByKey(String, String)
     */
    @Override
    public R<AuthorizationConsentDto> findByKey(String clientId, String principalName) {
        return R.success(null);
    }

}
