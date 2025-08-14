package top.baihu.platform.system.core.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.system.commons.constants.SystemConfigConstants;
import top.baihu.platform.system.core.domain.dto.ConfigDto;

/**
 * @author elvea
 */
public class ConfigManagerTests extends BaseTests {

    @Autowired
    ConfigManager configManager;

    @Test
    public void baseTest() {
        ConfigDto appTitle = this.configManager.get(SystemConfigConstants.APP_TITLE);
        ConfigDto appCopyright = this.configManager.get(SystemConfigConstants.APP_COPYRIGHT);
        Assertions.assertNotNull(appTitle);
        Assertions.assertNotNull(appCopyright);
    }

}
