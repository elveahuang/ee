package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.commons.api.CoreApi;
import cc.wdev.platform.system.core.domain.vo.InitializeVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class CoreApiImpl implements CoreApi {

    private final ConfigApi configApi;

    @Override
    public InitializeVo initialize() {
        return InitializeVo.builder()
            .loginCaptchaEnabled(this.configApi.getBoolean(LOGIN_CAPTCHA_ENABLED))
            .build();
    }

}
