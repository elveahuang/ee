package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import cc.wdev.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
public class UserSessionFeignClientImpl implements UserSessionFeignClient {

    private final UserSessionAmqpService userSessionAmqpService;

    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
