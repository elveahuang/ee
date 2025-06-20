package cc.elvea.platform.system.ai.web.app;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.ai.model.request.SimpleChatRequest;
import cc.elvea.platform.system.ai.model.request.SimpleCompletionRequest;
import cc.elvea.platform.system.ai.model.response.SimpleChatResponse;
import cc.elvea.platform.system.commons.constants.SystemAIConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__CHAT;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__COMPLETION;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
@RestController
@Tag(name = "AiController", description = "AiController")
public class AiController {

    private final DeepSeekChatModel model;

    private final ChatClient client;

    private final MessageWindowChatMemory messageWindowChatMemory;

    public AiController(DeepSeekChatModel model, ChatMemoryRepository chatMemoryRepository) {
        this.messageWindowChatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(SystemAIConstants.MAX_MESSAGES)
            .build();
        this.model = model;
        this.client = ChatClient.builder(model)
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build())
            .build();
    }

    @GetMapping(API_V1__AI__CHAT)
    public R<SimpleChatResponse> messages(@RequestParam(value = "conversationId", defaultValue = "") String conversationId) {
        conversationId = StringUtils.isNotEmpty(conversationId) ? conversationId : StringUtils.uuid();
        SimpleChatResponse response = SimpleChatResponse.builder()
            .conversationId(conversationId)
            .messages(messageWindowChatMemory.get(conversationId))
            .build();
        return R.success(response);
    }

    @PostMapping(API_V1__AI__CHAT)
    public Flux<String> chat(@RequestBody SimpleChatRequest request) {
        Flux<ChatResponse> flux = this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .stream()
            .chatResponse();
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

    @PostMapping(API_V1__AI__COMPLETION)
    public Flux<String> completion(@RequestBody SimpleCompletionRequest request) {
        Flux<ChatResponse> flux = this.model.stream(new Prompt(request.getPrompt()));
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

}
