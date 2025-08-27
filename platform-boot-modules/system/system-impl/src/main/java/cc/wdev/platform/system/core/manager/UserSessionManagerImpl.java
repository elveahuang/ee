package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import cc.wdev.platform.system.core.service.LoginSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Component
@AllArgsConstructor
public class UserSessionManagerImpl implements UserSessionManager {

    private final LoginSessionAmqpService loginSessionAmqpService;

    /**
     * @see UserSessionManager#saveUserSession(UserSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.loginSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
