package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.commons.constants.SystemServiceConstants;
import cc.elvea.platform.system.core.api.fallback.UserSessionApiFallback;
import cc.elvea.platform.system.core.domain.dto.UserSessionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME;

/**
 * @author elvea
 */
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = UserSessionApiFallback.class)
public interface UserSessionApi {

    @PostMapping(API_V1_FEIGN__USER_SESSION__FIND_BY_USERNAME)
    R<Boolean> saveUserSession(@SpringQueryMap UserSessionDto userSession) throws Exception;

}
