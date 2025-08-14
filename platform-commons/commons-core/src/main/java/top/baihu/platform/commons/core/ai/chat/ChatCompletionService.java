package top.baihu.platform.commons.core.ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;
import top.baihu.platform.commons.core.ai.domain.request.SimpleChatRequest;
import top.baihu.platform.commons.core.ai.domain.request.SimpleChatRequestCustomizer;
import top.baihu.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import top.baihu.platform.commons.core.ai.domain.request.SimpleCompletionRequestCustomizer;

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
