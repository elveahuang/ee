package cc.wdev.dev.webapp.web;

import cc.wdev.platform.commons.ai.AiManager;
import cc.wdev.platform.commons.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.ai.domain.response.SimpleChatResponse;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.web.bind.annotation.*;

/**
 * @author elvea
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "ChatController", description = "对话控制器")
public class ChatController {

    private final AiManager aiManager;

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
        ChatModel chatModel = aiManager.getChatModelFactory().getModel();
        ChatOptions chatOptions = ToolCallingChatOptions.builder().build();
        Prompt prompt = new Prompt(request.getPrompt(), chatOptions);
        String response = ChatClient.create(chatModel).prompt(prompt).call().content();
        System.out.println(response);
        return response;
    }

}
