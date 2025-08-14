package top.baihu.platform.system.core.api.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.system.core.api.UserApi;
import top.baihu.platform.system.core.domain.dto.UserLoginDto;

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
