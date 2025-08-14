package top.baihu.platform.system.ai.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;

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
