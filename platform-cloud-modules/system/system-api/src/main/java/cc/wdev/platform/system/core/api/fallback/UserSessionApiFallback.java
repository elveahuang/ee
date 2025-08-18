package cc.wdev.platform.system.core.api.fallback;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.api.UserSessionApi;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Slf4j
@Component
public class UserSessionApiFallback implements UserSessionApi {

    @Override
    public R<Boolean> saveUserSession(UserSessionDto userSession) {
        return R.success(Boolean.TRUE);
    }

}
