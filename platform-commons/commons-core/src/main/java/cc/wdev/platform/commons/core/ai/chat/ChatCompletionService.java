package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
public interface ChatCompletionService {

    ChatClient getChatClient();

    ChatResponse completion(SimpleCompletionRequest request);

    String completionText(SimpleCompletionRequest request);

    ChatResponse chatCompletion(SimpleChatRequest request);

    String chatCompletionText(SimpleChatRequest request);

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request);

    Flux<String> streamChatCompletionText(SimpleChatRequest request);

}
