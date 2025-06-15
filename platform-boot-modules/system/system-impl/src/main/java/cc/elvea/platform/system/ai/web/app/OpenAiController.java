package cc.elvea.platform.system.ai.web.app;

import cc.elvea.platform.commons.annotations.Anonymous;
import cc.elvea.platform.commons.base.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__CHAT;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__AI__COMPLETION;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "OpenAiController", description = "OpenAiController")
public class OpenAiController {

    private final DeepSeekChatModel model;

    @Anonymous
    @PostMapping(API_V1__AI__CHAT)
    public Flux<ChatResponse> chat(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return model.stream(prompt);
    }

    @PostMapping(API_V1__AI__COMPLETION)
    public R<?> completion() {
        return R.success();
    }

}
