package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.dev.webapp.ai.tools.CoreTools;
import cc.wdev.dev.webapp.ai.utils.AiUtils;
import cc.wdev.dev.webapp.ai.vo.JobVo;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

import static cn.hutool.core.util.StrUtil.uuid;

/**
 * @author elvea
 */
public class ChatServiceTests extends BaseTests {

    @Autowired
    private AiFactory aiFactory;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(this.aiFactory);
        Assertions.assertNotNull(this.aiFactory.getChatService());
        Assertions.assertNotNull(this.aiFactory.getChatService().getChatClient());
        Assertions.assertNotNull(this.aiFactory.getChatService().getChatModel());
    }

    @Test
    public void completionTest() {
        SimpleCompletionRequest request = SimpleCompletionRequest.builder()
            .prompt("你好")
            .build();

        ChatService chatService = aiFactory.getChatService();
        String content = chatService.completionText(request);
        Assertions.assertNotNull(content);
    }

    @Test
    public void toolTest() {
        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();
        ToolCallback[] toolCallbacks = ToolCallbacks.from(new CoreTools());
        ChatOptions options = ToolCallingChatOptions.builder()
            .toolCallbacks(toolCallbacks)
            .internalToolExecutionEnabled(false)
            .build();
        Prompt prompt = new Prompt("搜索产品经理职位", options);
        ChatResponse response = this.aiFactory.getChatService().completion(prompt);
        while (response.hasToolCalls()) {
            ToolExecutionResult result = toolCallingManager.executeToolCalls(prompt, response);
            prompt = new Prompt(result.conversationHistory(), options);
            response = this.aiFactory.getChatService().completion(prompt);
            List<JobVo> list = this.aiFactory.getChatService().getChatClient().prompt(prompt).call().entity(new ParameterizedTypeReference<>() {
            });
            Assertions.assertNotNull(list);
        }
        System.out.printf(response.toString());
    }

    @Test
    public void streamToolTest() {
        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();
        ToolCallback[] toolCallbacks = ToolCallbacks.from(new CoreTools());
        ChatOptions chatOptions = ToolCallingChatOptions.builder()
            .toolCallbacks(toolCallbacks)
            .internalToolExecutionEnabled(false)
            .build();
        final Prompt prompt = new Prompt("搜索产品经理职位", chatOptions);
        Flux<ChatResponse> publisher = this.aiFactory.getChatService().streamChatCompletion(prompt);
        publisher.map(response -> {
            boolean hasToolCall = false;
            Prompt fp = prompt;
            while (response.hasToolCalls()) {
                hasToolCall = true;
                ToolExecutionResult toolExecutionResult = toolCallingManager.executeToolCalls(fp, response);
                fp = new Prompt(toolExecutionResult.conversationHistory(), chatOptions);
                response = this.aiFactory.getChatService().completion(prompt);
            }
            if (hasToolCall) {
                return ServerSentEvent.builder(response.toString()).event("interaction").build();
            } else {
                return ServerSentEvent.builder(response.toString()).event("text").build();
            }
        });
    }

    @Test
    public void springTest() {
        SimpleChatRequest request = SimpleChatRequest.builder()
            .prompt("你好")
            .conversationId(uuid())
            .tool("getVersion")
            .build();
        AiUtils.handleRequest(request);

        ChatService chatService = aiFactory.getChatService();
        String content = chatService.chatCompletionText(request);
        Assertions.assertNotNull(content);
    }

}
