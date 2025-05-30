package cc.elvea.platform.system.security.api;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.commons.constants.SystemServiceConstants;
import cc.elvea.platform.system.security.model.dto.AuthorizationConsentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Component
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = AuthorizationConsentApi.AuthorizationConsentApiFallback.class)
public interface AuthorizationConsentApi {

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__SAVE)
    R<Boolean> save(@SpringQueryMap AuthorizationConsentDto saveDto);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__DELETE_BY_KEY)
    R<Boolean> deleteByKey(@RequestParam(value = "username") String clientId,
                           @RequestParam(value = "username") String principalName);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION_CONSENT__FIND_BY_KEY)
    R<AuthorizationConsentDto> findByKey(@RequestParam(value = "username") String clientId,
                                         @RequestParam(value = "username") String principalName);

    // -----------------------------------------------------------------------------------------------------------------
    // Fallback
    // -----------------------------------------------------------------------------------------------------------------

    @Component
    class AuthorizationConsentApiFallback implements AuthorizationConsentApi {

        @Override
        public R<Boolean> save(AuthorizationConsentDto saveDto) {
            return null;
        }

        @Override
        public R<Boolean> deleteByKey(String clientId, String principalName) {
            return null;
        }

        @Override
        public R<AuthorizationConsentDto> findByKey(String clientId, String principalName) {
            return null;
        }

    }

}
