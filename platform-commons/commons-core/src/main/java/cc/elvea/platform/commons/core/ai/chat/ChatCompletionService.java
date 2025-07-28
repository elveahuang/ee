package cc.elvea.platform.commons.core.ai.chat;

import cc.elvea.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.elvea.platform.commons.core.ai.domain.request.SimpleChatRequestCustomizer;
import cc.elvea.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import cc.elvea.platform.commons.core.ai.domain.request.SimpleCompletionRequestCustomizer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
public interface ChatCompletionService {

    ChatClient getChatClient();

    ChatResponse chatCompletion(SimpleChatRequest request) throws Exception;

    ChatResponse chatCompletion(SimpleChatRequest request, SimpleChatRequestCustomizer customizer) throws Exception;

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) throws Exception;

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request, SimpleCompletionRequestCustomizer customizer) throws Exception;

    ChatResponse completion(SimpleCompletionRequest request) throws Exception;

    String completionText(SimpleCompletionRequest request) throws Exception;

}
