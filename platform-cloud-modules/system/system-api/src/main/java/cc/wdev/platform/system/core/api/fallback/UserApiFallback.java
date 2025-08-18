package cc.wdev.platform.system.core.api.fallback;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.api.UserApi;
import cc.wdev.platform.system.core.domain.dto.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Slf4j
@Component
public class UserApiFallback implements UserApi {

    @Override
    public R<UserLoginDto> findByUsername(String username) {
        return R.success();
    }

}
