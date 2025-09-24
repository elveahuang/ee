package cc.wdev.platform.system.commons.manager;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.core.feign.KeywordApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class KeywordApiTests extends BaseTests {

    @Autowired
    KeywordApi keywordApi;

    @Test
    public void baseTest() {
        keywordApi.initialize();
    }

}
