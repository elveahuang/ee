package cc.wdev.platform.commons.core.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.dev.webapp.ai.vo.Courses;
import cc.wdev.platform.commons.ai.AiManager;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import cc.wdev.platform.commons.ai.tools.CommonTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
@Slf4j
public class AiTests extends BaseTests {

    @Autowired
    private AiManager aiManager;

    @Autowired
    private ToolCallbackProvider commonToolsProvider;

    @Test
    public void baseTest() {
        ChatClient chatClient = this.aiManager.getChatClient();
        ChatResponse response = chatClient.prompt()
            .user("你好")
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    public void baseToolTest() {
        ChatClient chatClient = this.aiManager.getChatClient();
        ChatResponse response = chatClient.prompt("获取当前系统版本号")
            .tools(new CommonTools())
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    public void userControlledToolTest() {
        ChatModelFactory chatModelFactory = this.aiManager.getChatModelFactory();
        ChatModel client = chatModelFactory.getChatModel();
        Assertions.assertNotNull(client);

        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();
        ChatModel chatModel = this.aiManager.getChatModelFactory().getModel();
        ChatOptions chatOptions = ToolCallingChatOptions.builder()
            .toolCallbacks(this.commonToolsProvider.getToolCallbacks())
            .internalToolExecutionEnabled(false)
            .build();
        Prompt prompt = new Prompt("今天几号", chatOptions);
        ChatResponse response = chatModel.call(prompt);
        while (response.hasToolCalls()) {
            ToolExecutionResult toolExecutionResult = toolCallingManager.executeToolCalls(prompt, response);
            prompt = new Prompt(toolExecutionResult.conversationHistory(), chatOptions);
            response = chatModel.call(prompt);
        }
        System.out.println(response.getResult().getOutput().getText());
        Assertions.assertNotNull(response);
    }

    /**
     * 简介结构化输出
     */
    @Test
    public void structuredOutputToolTest() {
        ChatModel chatModel = this.aiManager.getChatModelFactory().getModel();
        Courses courses = ChatClient.create(chatModel).prompt()
            .user(u -> u.text("今天几号"))
            .toolCallbacks(this.commonToolsProvider.getToolCallbacks())
            .call()
            .entity(Courses.class);
        Assertions.assertNotNull(courses);
    }


}
