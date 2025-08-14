package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.core.domain.vo.InitializeVo;

import static top.baihu.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

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
