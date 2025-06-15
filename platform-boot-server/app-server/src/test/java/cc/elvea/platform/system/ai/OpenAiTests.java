package cc.elvea.platform.system.ai;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class OpenAiTests extends BaseTests {

    @Autowired
    private OpenAiChatModel model;

    @Test
    public void test() {
        ChatResponse response = model.call(new Prompt(
            "你好，你是谁",
            DeepSeekChatOptions.builder().model(DeepSeekApi.ChatModel.DEEPSEEK_CHAT.getValue()).build()
        ));
        Assertions.assertNotNull(response);
    }

}
