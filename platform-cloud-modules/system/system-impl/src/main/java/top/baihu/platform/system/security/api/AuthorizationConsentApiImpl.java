package top.baihu.platform.system.security.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.security.domain.dto.AuthorizationConsentDto;

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
