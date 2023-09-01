package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER__FIND_BY_USERNAME;
import static cn.elvea.platform.system.commons.constants.SystemServiceConstants.SYSTEM_SERVICE;

/**
 * @author elvea
 * @since 0.0.1
 */
@Component
@FeignClient(name = SYSTEM_SERVICE, fallback = UserApi.UserApiFallback.class)
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
