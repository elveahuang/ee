package cc.wdev.platform.system.core.feign.fallback;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.core.domain.dto.UserLoginDto;
import cc.wdev.platform.system.core.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Slf4j
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public R<UserLoginDto> findByUsername(String username) {
        return R.success();
    }

}
