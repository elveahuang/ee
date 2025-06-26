package cc.elvea.platform.commons.core.ai.chat;

import cc.elvea.platform.commons.core.ai.model.request.SimpleChatRequest;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
public interface ChatCompletionService {

    ChatResponse chatCompletion(SimpleChatRequest request) throws Exception;

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) throws Exception;

}
