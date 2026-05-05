package cc.wdev.dev.webapp.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.ai.AiManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class AiTests extends BaseTests {

    @Autowired
    private AiManager aiManager;

    @Test
    public void baseToolTest() {
        ChatModel model = aiManager.getChatModel();
        String content = ChatClient.create(model)
            .prompt("你好，现在时间是？")
            .toolNames("getVersion")
            .call()
            .content();
        Assertions.assertNotNull(content);
    }

    @Test
    public void baseBookTest() {
        ChatModel model = aiManager.getChatModel();
        String content = ChatClient.create(model)
            .prompt("有没有古典文学书籍介绍")
            .toolNames("getBooks")
            .call()
            .content();
        Assertions.assertNotNull(content);
    }

    @Test
    public void toolNamesTest() {
        ChatModel model = aiManager.getChatModel();
        String content = ChatClient.create(model)
            .prompt("跟李四")
            .toolNames("getBooks")
            .call()
            .content();
        Assertions.assertNotNull(content);
    }

}
