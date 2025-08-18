package cc.wdev.platform.system.ai.service;

import cc.wdev.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class AiChatMemoryServiceTests extends BaseTests {

    @Autowired
    private AiChatMemoryService aiChatMemoryService;

    @Test
    public void test() {
        List<String> ids = aiChatMemoryService.findConversationIds();
        Assertions.assertNotNull(ids);
    }

}
