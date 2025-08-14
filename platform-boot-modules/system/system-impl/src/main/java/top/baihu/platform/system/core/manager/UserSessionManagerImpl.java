package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;
import top.baihu.platform.system.core.service.UserSessionAmqpService;

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
