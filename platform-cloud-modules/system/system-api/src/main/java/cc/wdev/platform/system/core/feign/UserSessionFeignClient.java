package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.commons.constants.SystemServiceConstants;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import cc.wdev.platform.system.core.feign.fallback.UserSessionFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME;

/**
 * @author elvea
 */
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = UserSessionFeignClientFallback.class)
public interface UserSessionFeignClient {

    @PostMapping(API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME)
    R<Boolean> saveUserSession(@SpringQueryMap UserSessionDto userSession) throws Exception;

}
