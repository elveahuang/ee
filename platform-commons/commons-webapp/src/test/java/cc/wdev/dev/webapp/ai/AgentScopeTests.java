package cc.wdev.dev.webapp.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.ai.AiManager;
import io.agentscope.core.ReActAgent;
import io.agentscope.core.message.Msg;
import io.agentscope.core.model.OpenAIChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class AgentScopeTests extends BaseTests {

    @Autowired
    private AiManager aiManager;

    @Test
    public void baseTest() {
        OpenAIChatModel model = OpenAIChatModel.builder()
            .apiKey(System.getenv("DEEPSEEK_API_KEY"))
            .modelName("deepseek-chat")
            .baseUrl("https://api.deepseek.com")
            .build();

        ReActAgent jarvis = ReActAgent.builder()
            .name("Jarvis")
            .sysPrompt("You are an assistant named Jarvis.")
            .model(model)
            .build();

        // Send message
        Msg msg = Msg.builder()
            .textContent("你好")
            .build();

        Msg response = jarvis.call(msg).block();
        System.out.println(response.getTextContent());
    }

}
