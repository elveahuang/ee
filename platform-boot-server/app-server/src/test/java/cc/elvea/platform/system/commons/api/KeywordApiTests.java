package cc.elvea.platform.system.commons.api;

import cc.elvea.platform.BaseTests;
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
