package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequestCustomizer;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequestCustomizer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
public interface ChatCompletionService {

    ChatClient getChatClient();

    ChatResponse chatCompletion(SimpleChatRequest request);

    ChatResponse chatCompletion(SimpleChatRequest request, SimpleChatRequestCustomizer customizer);

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request);

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request, SimpleCompletionRequestCustomizer customizer);

    ChatResponse completion(SimpleCompletionRequest request);

    String completionText(SimpleCompletionRequest request);

}
