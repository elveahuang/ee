package cc.wdev.dev.webapp.web;

import cc.wdev.dev.webapp.ai.constant.AiConstant;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.response.SimpleChatResponse;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "ChatController", description = "对话控制器")
public class ChatController {

    private AiFactory aiFactory;

    @GetMapping("/chat/start")
    public R<SimpleChatResponse> messages(@RequestParam(value = "conversationId", defaultValue = "") String conversationId) {
        conversationId = StringUtils.isNotEmpty(conversationId) ? conversationId : StringUtils.uuid();
        SimpleChatResponse response = SimpleChatResponse.builder()
            .conversationId(conversationId)
            .messages(Lists.newArrayList())
            .build();
        return R.success(response);
    }

    @PostMapping("/chat")
    public Flux<ServerSentEvent<String>> chatCompletion(@RequestBody SimpleChatRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();
        request.setSystemPrompt(prompt);
        Flux<ChatResponse> publisher = aiFactory.getChatCompletionService().streamChatCompletion(request);

        return publisher.flatMap(response -> {
            Generation result = response.getResult();
            String content = result.getOutput().toString();
            return Flux.just(ServerSentEvent.builder(content)
                .event("text")
                .build());
        });
    }

}
