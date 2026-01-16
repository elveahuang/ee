package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;

/**
 * @author elvea
 */
public interface ChatService {

    ChatClient getChatClient();

    ChatModel getChatModel();

    String completionText(SimpleCompletionRequest request);

    String completionText(Prompt prompt);

    ChatResponse completion(SimpleCompletionRequest request);

    ChatResponse completion(Prompt prompt);

    ChatResponse chatCompletion(SimpleChatRequest request);

    ChatResponse chatCompletion(Prompt prompt);

    String chatCompletionText(SimpleChatRequest request);

    Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request);

    Flux<String> streamChatCompletionText(SimpleChatRequest request);

    Flux<ChatResponse> streamChatCompletion(Prompt prompt);

}
