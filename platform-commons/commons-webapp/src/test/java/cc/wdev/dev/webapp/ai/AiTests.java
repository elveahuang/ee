package cc.wdev.dev.webapp.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.dev.webapp.ai.constant.AiConstant;
import cc.wdev.dev.webapp.ai.vo.Courses;
import cc.wdev.platform.commons.core.ai.AiManager;
import cc.wdev.platform.commons.core.ai.chat.ChatService;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
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
        Assertions.assertNotNull(aiManager);
        ChatService service = this.aiManager.getChatService();
        Assertions.assertNotNull(service);
        ChatClient client = service.getChatClient();
        Assertions.assertNotNull(client);

        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();

        ChatResponse response = client.prompt()
            .system(prompt)
            .user("我是dada，有没有适合我的课程")
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    public void streamTest() {
        Assertions.assertNotNull(aiManager);
        ChatService service = this.aiManager.getChatService();
        Assertions.assertNotNull(service);
        ChatClient client = service.getChatClient();
        Assertions.assertNotNull(client);

        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();

        ChatResponse response = client.prompt()
            .system(prompt)
            .user("我是dada，有没有适合我的课程")
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    public void userControlledToolTest() {
        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();
        ChatModel chatModel = this.aiManager.getChatModel();
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
        ChatModel chatModel = this.aiManager.getChatModel();
        Courses courses = ChatClient.create(chatModel).prompt()
            .user(u -> u.text("今天几号"))
            .toolCallbacks(this.commonToolsProvider.getToolCallbacks())
            .call()
            .entity(Courses.class);
        Assertions.assertNotNull(courses);
    }

    @Test
    public void toolTest() {
        SimpleChatRequest request = SimpleChatRequest.builder()
            .prompt("三国演义相关课程")
            .build();
        ChatResponse response = this.aiManager.getChatService().chatCompletion(request);
        Assertions.assertNotNull(response);
    }

}
