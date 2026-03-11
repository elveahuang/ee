package cc.wdev.platform.commons.core.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.core.ai.tencent.AiTencentManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class AiTencentTests extends BaseTests {

    @Autowired
    private AiTencentManager aiTencentManager;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(this.aiTencentManager);
    }

}
