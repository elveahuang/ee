package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.api.UserSessionApi;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import cn.elvea.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 0.0.1
 */
@Component
@AllArgsConstructor
public class UserSessionApiImpl implements UserSessionApi {

    private final UserSessionAmqpService userSessionAmqpService;

    /**
     * @see UserSessionApi#saveUserSession(UserSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
