package cc.wdev.platform.system.security.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.commons.constants.SystemServiceConstants;
import cc.wdev.platform.system.security.domain.dto.AuthorizationConsentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Component
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = AuthorizationConsentFeignClient.AuthorizationConsentFeignClientFallback.class)
public interface AuthorizationConsentFeignClient {

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
    class AuthorizationConsentFeignClientFallback implements AuthorizationConsentFeignClient {

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
