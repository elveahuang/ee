package top.baihu.platform.system.core.api.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.core.api.UserSessionApi;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;

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
