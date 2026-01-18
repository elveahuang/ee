package cc.wdev.platform.system.security.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.commons.constants.SystemServiceConstants;
import cc.wdev.platform.system.security.domain.dto.AuthorizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Component
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = AuthorizationFeignClient.AuthorizationFeignClientFallback.class)
public interface AuthorizationFeignClient {

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__SAVE)
    R<Boolean> save(@RequestBody AuthorizationDto saveDto);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__DELETE_BY_ID)
    R<Boolean> deleteById(@RequestParam("id") Long id);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_ID)
    R<AuthorizationDto> findById(@RequestParam("id") Long state);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_STATE)
    R<AuthorizationDto> findByState(@RequestParam("state") String state);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_AUTHORIZATION_CODE_VALUE)
    R<AuthorizationDto> findByAuthorizationCodeValue(@RequestParam("code") String code);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_OIDC_ID_TOKEN_VALUE)
    R<AuthorizationDto> findByOidcIdTokenValue(@RequestParam("token") String token);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_ACCESS_TOKEN_VALUE)
    R<AuthorizationDto> findByAccessTokenValue(@RequestParam("token") String token);

    @PostMapping(API_V1_FEIGN__SECURITY__AUTHORIZATION__FIND_BY_REFRESH_TOKEN_VALUE)
    R<AuthorizationDto> findByRefreshTokenValue(@RequestParam("token") String token);

    // -----------------------------------------------------------------------------------------------------------------
    // Fallback
    // -----------------------------------------------------------------------------------------------------------------

    @Component
    class AuthorizationFeignClientFallback implements AuthorizationFeignClient {

        @Override
        public R<Boolean> save(AuthorizationDto saveDto) {
            return null;
        }

        @Override
        public R<Boolean> deleteById(Long id) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findById(Long state) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findByState(String state) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findByAuthorizationCodeValue(String code) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findByOidcIdTokenValue(String token) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findByAccessTokenValue(String token) {
            return null;
        }

        @Override
        public R<AuthorizationDto> findByRefreshTokenValue(String token) {
            return null;
        }

    }

}
