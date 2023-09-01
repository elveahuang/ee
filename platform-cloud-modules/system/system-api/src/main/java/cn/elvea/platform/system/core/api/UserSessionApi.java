package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME;
import static cn.elvea.platform.system.commons.constants.SystemServiceConstants.SYSTEM_SERVICE;

/**
 * @author elvea
 * @since 0.0.1
 */
@Component
@FeignClient(name = SYSTEM_SERVICE, fallback = UserSessionApi.UserSessionApiFallback.class)
public interface UserSessionApi {

    @PostMapping(API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME)
    R<Boolean> saveUserSession(@SpringQueryMap UserSessionDto userSession);

    // -----------------------------------------------------------------------------------------------------------------
    // Fallback
    // -----------------------------------------------------------------------------------------------------------------

    @Slf4j
    @Component
    class UserSessionApiFallback implements UserSessionApi {

        @Override
        public R<Boolean> saveUserSession(UserSessionDto userSession) {
            return R.success(Boolean.TRUE);
        }

    }

}
