package cc.wdev.dev.webapp.web;

import cc.wdev.dev.webapp.ai.constant.AiConstant;
import cc.wdev.dev.webapp.ai.vo.Courses;
import cc.wdev.platform.commons.core.ai.AiManager;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.response.SimpleChatResponse;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
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
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "ChatController", description = "对话控制器")
public class ChatController {

    private final AiManager aiManager;

    private final ToolCallbackProvider commonToolsProvider;

    @GetMapping("/chat")
    public R<SimpleChatResponse> chatStart(@RequestParam(value = "conversationId", defaultValue = "") String conversationId) {
        conversationId = StringUtils.isNotEmpty(conversationId) ? conversationId : StringUtils.uuid();
        SimpleChatResponse response = SimpleChatResponse.builder()
            .conversationId(conversationId)
            .messages(Lists.newArrayList())
            .build();
        return R.success(response);
    }

    @PostMapping("/chat")
    public String chatCompletion(@RequestBody SimpleChatRequest request) {
        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();

        ChatModel chatModel = aiManager.getChatModel();
        ChatOptions chatOptions = ToolCallingChatOptions.builder()
            .toolCallbacks(this.commonToolsProvider.getToolCallbacks())
            .internalToolExecutionEnabled(false)
            .build();
        Prompt prompt = new Prompt(request.getPrompt(), chatOptions);
        ChatResponse response = ChatClient.create(chatModel).prompt(prompt).call().chatResponse();
        while (response.hasToolCalls()) {
            ToolExecutionResult toolExecutionResult = toolCallingManager.executeToolCalls(prompt, response);
            prompt = new Prompt(toolExecutionResult.conversationHistory(), chatOptions);
            Courses courses = ChatClient.create(chatModel).prompt(prompt).call().entity(Courses.class);
            System.out.println(courses);
        }
        System.out.println(response.getResult().getOutput().getText());
        return "response.getResult().getOutput().getText();";
    }

    public Flux<ServerSentEvent<String>> chatCompletion1(@RequestBody SimpleChatRequest request) {
        boolean interaction = StringUtils.isNotEmpty(request.getTool());
        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();
        request.setSystemPrompt(prompt);
        Flux<ChatResponse> publisher = aiManager.getChatService().streamChatCompletion(request);
        try {
            return publisher.map(response -> {
                String content = response.getResult().getOutput().getText();
                return StringUtils.nvl(content);
            }).map(content -> {
                if (StringUtils.isEmpty(content)) {
                    return ServerSentEvent.builder("").event(interaction ? "interaction" : "text").build();
                } else {
                    return ServerSentEvent.builder(content).event(interaction ? "interaction" : "text").build();
                }
            });
        } catch (Exception e) {
            return Flux.just(ServerSentEvent.builder("Timeout").event("error").build());
        }
    }

}
