package cc.elvea.platform.system.core.api.impl;

import cc.elvea.platform.commons.web.R;
import cc.elvea.platform.system.core.api.UserSessionApi;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 24.1.0
 */
@AllArgsConstructor
@RestController
public class UserSessionApiImpl implements UserSessionApi {

    private final UserSessionAmqpService userSessionAmqpService;

    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
