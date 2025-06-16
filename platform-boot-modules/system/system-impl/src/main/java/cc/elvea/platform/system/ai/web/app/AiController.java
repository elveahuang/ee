package cc.elvea.platform.system.ai.web.app;

import cc.elvea.platform.system.ai.model.request.SimpleCompletionRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__CHAT;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__COMPLETION;

/**
 * @author elvea
 */
@RestController
@Tag(name = "AiController", description = "AiController")
public class AiController {

    private final DeepSeekChatModel model;

    private final ChatClient client;

    public AiController(DeepSeekChatModel model) {
        this.model = model;
        this.client = ChatClient.builder(model).build();
    }

    public Flux<String> chat(HttpServletResponse response,
                             @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        response.setCharacterEncoding("UTF-8");
        return client.prompt(new Prompt(message)).stream().content();
    }

    @GetMapping(API_V1__AI__CHAT)
    public String start() {
        return "";
    }

    @PostMapping(API_V1__AI__CHAT)
    public Flux<String> chat(@RequestBody String message) {
        Flux<ChatResponse> flux = this.model.stream(new Prompt(message));
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

    @PostMapping(API_V1__AI__COMPLETION)
    public Flux<String> completion(@RequestBody SimpleCompletionRequest request) {
        Flux<ChatResponse> flux = this.model.stream(new Prompt(request.getPrompt()));
        return flux.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

}
