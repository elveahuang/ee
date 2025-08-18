package cc.wdev.platform.system.ai;

import cc.wdev.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class DeepSeekTests extends BaseTests {

    @Autowired
    private DeepSeekChatModel model;

    @Test
    public void test() {
        ChatResponse response = model.call(new Prompt(
            "你好，你是谁",
            DeepSeekChatOptions.builder().model(DeepSeekApi.ChatModel.DEEPSEEK_CHAT.getValue()).build()
        ));
        Assertions.assertNotNull(response);
    }

    @Test
    public void chatClientTest() {
        ChatClient chatClient = ChatClient.builder(model).build();
    }

}
