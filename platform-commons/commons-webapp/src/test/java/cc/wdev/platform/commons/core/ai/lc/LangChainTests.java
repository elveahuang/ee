package cc.wdev.platform.commons.core.ai.lc;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;

public class LangChainTests {

    @Test
    public void baseTest() {
        ChatModel model = OpenAiChatModel.builder()
            .baseUrl("https://api.deepseek.com")
            .apiKey(System.getenv("AI_LC_DEEPSEEK_API_KEY"))
            .modelName("deepseek-chat")
            .build();
        String answer = model.chat("Say 'Hello World'");
        System.out.println(answer);
    }

}
