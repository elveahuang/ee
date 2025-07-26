package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Component
@AllArgsConstructor
public class UserSessionManagerImpl implements UserSessionManager {

    private final UserSessionAmqpService userSessionAmqpService;

    /**
     * @see UserSessionManager#saveUserSession(UserSessionDto)
     */
    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception {
        this.userSessionAmqpService.send(userSession);
        return R.success(Boolean.TRUE);
    }

}
