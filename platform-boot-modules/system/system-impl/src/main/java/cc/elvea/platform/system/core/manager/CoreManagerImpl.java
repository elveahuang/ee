package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.system.core.model.vo.InitializeVo;
import cc.elvea.platform.system.core.manager.ConfigManager;
import cc.elvea.platform.system.core.manager.CoreManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static cc.elvea.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

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
