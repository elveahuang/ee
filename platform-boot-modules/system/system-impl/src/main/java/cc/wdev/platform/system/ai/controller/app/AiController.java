package cc.wdev.platform.system.ai.controller.app;

import cc.wdev.platform.commons.core.ai.AiManager;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.response.SimpleChatResponse;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@Tag(name = "AiController", description = "AiController")
public class AiController {

    private final AiManager aiManager;

    public AiController(AiManager aiManager) {
        this.aiManager = aiManager;
    }

    @GetMapping(API_V1__AI__CHAT)
    public R<SimpleChatResponse> messages(@RequestParam(value = "conversationId", required = false, defaultValue = "") String conversationId,
                                          @RequestParam(value = "force", required = false, defaultValue = "false") boolean force) {
        conversationId = (StringUtils.isEmpty(conversationId) || force) ? StringUtils.uuid() : conversationId;
        SimpleChatResponse response = SimpleChatResponse.builder()
            .conversationId(conversationId)
            .messages(aiManager.getChatMemory().get(conversationId))
            .build();
        return R.success(response);
    }

    @PostMapping(API_V1__AI__CHAT)
    public String chatCompletion(@RequestBody SimpleChatRequest request) throws Exception {
        ChatResponse response = this.aiManager.getChatService().chatCompletion(request);
        return response.getResult().getOutput().getText();
    }

    @PostMapping(API_V1__AI__CHAT_STREAM)
    public Flux<String> streamChatCompletion(@RequestBody SimpleChatRequest request) throws Exception {
        Flux<ChatResponse> flux = this.aiManager.getChatService().streamChatCompletion(request);
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

    @PostMapping(API_V1__AI__COMPLETION)
    public Flux<String> completion(@RequestBody SimpleChatRequest request) throws Exception {
        Flux<ChatResponse> flux = this.aiManager.getChatService().streamChatCompletion(request);
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

}
