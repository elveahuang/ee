package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.commons.constants.SystemServiceConstants;
import cc.elvea.platform.system.core.api.fallback.UserApiFallback;
import cc.elvea.platform.system.core.domain.dto.UserLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER__FIND_BY_USERNAME;

/**
 * @author elvea
 */
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = UserApiFallback.class)
public interface UserApi {

    @PostMapping(API_V1_FEIGN__USER__FIND_BY_USERNAME)
    R<UserLoginDto> findByUsername(@RequestParam(value = "username") String username);

}
