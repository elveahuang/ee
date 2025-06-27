package cc.elvea.platform.commons.core.ai.chat;

import cc.elvea.platform.commons.core.ai.advisor.CustomLoggingAdvisor;
import cc.elvea.platform.commons.core.ai.model.request.SimpleChatRequest;
import cc.elvea.platform.commons.core.ai.tools.CommonTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public class DefaultChatCompletionService implements ChatCompletionService {

    private final ChatClient client;

    public DefaultChatCompletionService(DeepSeekChatModel model,
                                        MessageWindowChatMemory messageWindowChatMemory) {
        this.client = ChatClient.builder(model).defaultAdvisors(
            MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build(),
            new CustomLoggingAdvisor()
        ).defaultTools(new CommonTools()).build();
    }

    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request) throws Exception {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .call()
            .chatResponse();
    }

    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) throws Exception {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .stream()
            .chatResponse();
    }

}
