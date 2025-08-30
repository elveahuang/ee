package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;
import cc.wdev.platform.system.core.service.LoginSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Component
@AllArgsConstructor
public class LoginSessionManagerImpl implements LoginSessionManager {

    private final LoginSessionAmqpService loginSessionAmqpService;

    /**
     * @see LoginSessionManager#saveUserSession(LoginSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(LoginSessionDto dto) throws Exception {
        this.loginSessionAmqpService.send(dto);
        return R.success(Boolean.TRUE);
    }

}
