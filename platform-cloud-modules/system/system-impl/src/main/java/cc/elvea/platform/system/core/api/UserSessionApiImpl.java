package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.web.response.R;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
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
