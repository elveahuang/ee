package cc.wdev.platform.system.core.api;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.commons.constants.SystemConfigConstants;
import cc.wdev.platform.system.core.domain.dto.ConfigDto;
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
