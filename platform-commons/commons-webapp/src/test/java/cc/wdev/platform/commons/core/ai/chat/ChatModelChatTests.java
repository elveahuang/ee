package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.dev.webapp.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class ChatModelChatTests extends BaseTests {

    @Autowired
    OpenAiChatModel defaultChatModel;

    @Test
    public void openAiTest() {
        Prompt prompt = new Prompt("你好，你是谁？");
        ChatResponse response = this.defaultChatModel.call(prompt);
        String result = response.getResults().getFirst().getOutput().getText();
        Assertions.assertNotNull(result);
    }

}
