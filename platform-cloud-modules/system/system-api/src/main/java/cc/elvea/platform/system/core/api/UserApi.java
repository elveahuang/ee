package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.commons.constants.SystemServiceConstants;
import cc.elvea.platform.system.core.model.dto.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER__FIND_BY_USERNAME;

/**
 * @author elvea
 */
@Component
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = UserApi.UserApiFallback.class)
public interface UserApi {

    @PostMapping(API_V1_FEIGN__USER__FIND_BY_USERNAME)
    R<UserLoginDto> findByUsername(@RequestParam(value = "username") String username);

    // -----------------------------------------------------------------------------------------------------------------
    // Fallback
    // -----------------------------------------------------------------------------------------------------------------

    @Slf4j
    @Component
    class UserApiFallback implements UserApi {

        @Override
        public R<UserLoginDto> findByUsername(String username) {
            return R.success();
        }

    }

}
