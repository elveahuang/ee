package cc.elvea.platform.system.core.api.fallback;

import cc.elvea.platform.commons.domain.R;
import cc.elvea.platform.system.core.api.UserApi;
import cc.elvea.platform.system.core.domain.dto.UserLoginDto;
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
