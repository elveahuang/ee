package cc.elvea.platform.system.core.api.impl;

import cc.elvea.platform.commons.web.R;
import cc.elvea.platform.system.core.api.UserSessionApi;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 24.1.0
 */
@Component
@AllArgsConstructor
public class UserSessionApiImpl implements UserSessionApi {

    private final UserSessionAmqpService userSessionAmqpService;

    /**
     * @see UserSessionApi#saveUserSession(UserSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
