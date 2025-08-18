package cc.wdev.platform.system.commons.manager;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.core.manager.KeywordManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class KeywordManagerTests extends BaseTests {

    @Autowired
    KeywordManager keywordManager;

    @Test
    public void baseTest() {
        keywordManager.initialize();
    }

}
