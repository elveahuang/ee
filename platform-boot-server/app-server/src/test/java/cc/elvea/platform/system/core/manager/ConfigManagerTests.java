package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemConfigConstants;
import cc.elvea.platform.system.core.domain.dto.ConfigDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
