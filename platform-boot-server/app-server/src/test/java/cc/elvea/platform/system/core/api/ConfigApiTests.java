package cc.elvea.platform.system.core.api;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemConfigConstants;
import cc.elvea.platform.system.config.api.ConfigApi;
import cc.elvea.platform.system.config.model.dto.ConfigDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class ConfigApiTests extends BaseTests {

    @Autowired
    ConfigApi configApi;

    @Test
    public void baseTest() {
        ConfigDto appTitle = this.configApi.get(SystemConfigConstants.APP_TITLE);
        ConfigDto appCopyright = this.configApi.get(SystemConfigConstants.APP_COPYRIGHT);
        Assertions.assertNotNull(appTitle);
        Assertions.assertNotNull(appCopyright);
    }

}
