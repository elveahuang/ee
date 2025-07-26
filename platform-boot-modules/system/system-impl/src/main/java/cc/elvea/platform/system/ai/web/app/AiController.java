package cc.elvea.platform.system.ai.web.app;

import cc.elvea.platform.commons.core.ai.AiFactory;
import cc.elvea.platform.commons.core.ai.model.request.SimpleChatRequest;
import cc.elvea.platform.commons.core.ai.model.response.SimpleChatResponse;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.web.response.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@Tag(name = "AiController", description = "AiController")
public class AiController {

    private final AiFactory factory;

    public AiController(AiFactory factory) {
        this.factory = factory;
    }

    @GetMapping(API_V1__AI__CHAT)
    public R<SimpleChatResponse> messages(@RequestParam(value = "conversationId", required = false, defaultValue = "") String conversationId,
                                          @RequestParam(value = "force", required = false, defaultValue = "false") boolean force) {
        conversationId = (StringUtils.isEmpty(conversationId) || force) ? StringUtils.uuid() : conversationId;
        SimpleChatResponse response = SimpleChatResponse.builder()
            .conversationId(conversationId)
            .messages(factory.getMessageWindowChatMemory().get(conversationId))
            .build();
        return R.success(response);
    }

    @PostMapping(API_V1__AI__CHAT)
    public String chatCompletion(@RequestBody SimpleChatRequest request) throws Exception {
        ChatResponse response = this.factory.getChatCompletionService().chatCompletion(request);
        return response.getResult().getOutput().getText();
    }

    @PostMapping(API_V1__AI__CHAT_STREAM)
    public Flux<String> streamChatCompletion(@RequestBody SimpleChatRequest request) throws Exception {
        Flux<ChatResponse> flux = this.factory.getChatCompletionService().streamChatCompletion(request);
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

    @PostMapping(API_V1__AI__COMPLETION)
    public Flux<String> completion(@RequestBody SimpleChatRequest request) throws Exception {
        Flux<ChatResponse> flux = this.factory.getChatCompletionService().streamChatCompletion(request);
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

}
