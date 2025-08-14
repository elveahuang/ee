package top.baihu.platform.system.core.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;
import top.baihu.platform.system.core.service.UserSessionAmqpService;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
public class UserSessionApiImpl implements UserSessionApi {

    private final UserSessionAmqpService userSessionAmqpService;

    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
