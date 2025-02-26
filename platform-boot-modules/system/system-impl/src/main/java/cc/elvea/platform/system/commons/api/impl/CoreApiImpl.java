package cc.elvea.platform.system.commons.api.impl;

import cc.elvea.platform.system.commons.api.CoreApi;
import cc.elvea.platform.system.commons.model.vo.InitializeVo;
import cc.elvea.platform.system.config.api.ConfigApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static cc.elvea.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

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
