package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.system.core.domain.vo.InitializeVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class CoreManagerImpl implements CoreManager {

    private final ConfigManager configManager;

    @Override
    public InitializeVo initialize() {
        return InitializeVo.builder()
            .loginCaptchaEnabled(this.configManager.getBoolean(LOGIN_CAPTCHA_ENABLED))
            .build();
    }

}
