package cc.wdev.platform.system.security.api;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.security.domain.dto.AuthorizationConsentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
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
