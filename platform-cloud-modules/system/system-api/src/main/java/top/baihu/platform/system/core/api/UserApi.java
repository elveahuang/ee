package top.baihu.platform.system.core.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.commons.constants.SystemServiceConstants;
import top.baihu.platform.system.core.api.fallback.UserApiFallback;
import top.baihu.platform.system.core.domain.dto.UserLoginDto;

import static top.baihu.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER__FIND_BY_USERNAME;

/**
 * @author elvea
 */
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = UserApiFallback.class)
public interface UserApi {

    @PostMapping(API_V1_FEIGN__USER__FIND_BY_USERNAME)
    R<UserLoginDto> findByUsername(@RequestParam(value = "username") String username);

}
