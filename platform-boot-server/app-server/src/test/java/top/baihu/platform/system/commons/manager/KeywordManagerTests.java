package top.baihu.platform.system.commons.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.system.core.manager.KeywordManager;

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
