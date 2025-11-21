package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.advisor.CustomLoggingAdvisor;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequestCustomizer;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequestCustomizer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.ObjectProvider;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public class DefaultChatCompletionService implements ChatCompletionService {

    private final ChatClient client;

    public DefaultChatCompletionService(OpenAiChatModel model,
                                        MessageWindowChatMemory messageWindowChatMemory,
                                        ObjectProvider<AiCustomizer> customizerProvider) {
        AiCustomizer customizer = customizerProvider.getIfAvailable(AiCustomizer::defaultCustomizer);

        this.client = ChatClient.builder(model)
            .defaultAdvisors(
                MessageChatMemoryAdvisor
                    .builder(messageWindowChatMemory)
                    .scheduler(MessageChatMemoryAdvisor.DEFAULT_SCHEDULER)
                    .build(),
                new CustomLoggingAdvisor()
            )
            .defaultTools(customizer.getTools().toArray())
            .build();
    }

    /**
     * @see ChatCompletionService#getChatClient()
     */
    @Override
    public ChatClient getChatClient() {
        return this.client;
    }

    /**
     * @see ChatCompletionService#chatCompletion(SimpleChatRequest)
     */
    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request) {
        return this.chatCompletion(request, SimpleChatRequestCustomizer.builder().build());
    }

    /**
     * @see ChatCompletionService#chatCompletion(SimpleChatRequest, SimpleChatRequestCustomizer)
     */
    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request,
                                       SimpleChatRequestCustomizer customizer) {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .call()
            .chatResponse();
    }

    /**
     * @see ChatCompletionService#streamChatCompletion(SimpleChatRequest)
     */
    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) {
        return this.streamChatCompletion(request, SimpleCompletionRequestCustomizer.builder().build());
    }

    /**
     * @see ChatCompletionService#streamChatCompletion(SimpleChatRequest, SimpleCompletionRequestCustomizer)
     */
    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request,
                                                   SimpleCompletionRequestCustomizer customizer) {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .stream()
            .chatResponse();
    }

    /**
     * @see ChatCompletionService#completion(SimpleCompletionRequest)
     */
    @Override
    public ChatResponse completion(SimpleCompletionRequest request) {
        OpenAiChatOptions options = new OpenAiChatOptions();
        return this.client
            .prompt(request.getPrompt())
            .options(options)
            .call()
            .chatResponse();

    }

    /**
     * @see ChatCompletionService#completionText(SimpleCompletionRequest)
     */
    @Override
    public String completionText(SimpleCompletionRequest request) {
        ChatResponse response = this.completion(request);
        if (response == null || response.getResult().getOutput().getText() == null) {
            return "";
        }
        return response.getResult().getOutput().getText();
    }

}
