package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;
import cc.wdev.platform.system.core.service.LoginSessionRabbitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Component
@AllArgsConstructor
public class LoginSessionApiImpl implements LoginSessionApi {

    private final LoginSessionRabbitService loginSessionRabbitService;

    /**
     * @see LoginSessionApi#saveUserSession(LoginSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(LoginSessionDto dto) throws Exception {
        this.loginSessionRabbitService.send(dto);
        return R.success(Boolean.TRUE);
    }

}
