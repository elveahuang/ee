package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.advisor.CustomLoggingAdvisor;
import cc.wdev.platform.commons.core.ai.advisor.UserContextChatMemoryAdvisor;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.ObjectProvider;
import reactor.core.publisher.Flux;

import java.util.List;

import static cc.wdev.platform.commons.core.ai.AiUtils.*;

/**
 * @author elvea
 */
public class DefaultChatService implements ChatService {

    private final ChatClient chatClient;

    private final ChatModel chatModel;

    public DefaultChatService(ChatModel chatModel,
                              ChatMemory chatMemory,
                              ObjectProvider<AiCustomizer> customizerProvider) {
        AiCustomizer customizer = customizerProvider.getIfAvailable(AiCustomizer::defaultCustomizer);

        this.chatModel = chatModel;
        this.chatClient = ChatClient.builder(chatModel).defaultAdvisors(
                new UserContextChatMemoryAdvisor(),
                MessageChatMemoryAdvisor.builder(chatMemory).scheduler(MessageChatMemoryAdvisor.DEFAULT_SCHEDULER).build(),
                new CustomLoggingAdvisor()
            )
            .defaultTools(customizer.getTools().toArray())
            .build();
    }

    /**
     * @see ChatService#getChatClient()
     */
    @Override
    public ChatClient getChatClient() {
        return this.chatClient;
    }

    /**
     * @see ChatService#getChatModel()
     */
    @Override
    public ChatModel getChatModel() {
        return this.chatModel;
    }

    /**
     * @see ChatService#completionText(SimpleCompletionRequest)
     */
    @Override
    public String completionText(SimpleCompletionRequest request) {
        List<Message> messages = processCompletionMessages(request);
        ChatOptions options = processCompletionOptions(request);
        return this.completionText(new Prompt(messages, options));
    }

    /**
     * @see ChatService#completionText(Prompt)
     */
    @Override
    public String completionText(Prompt prompt) {
        return this.chatModel.call(prompt).getResult().getOutput().getText();
    }

    /**
     * @see ChatService#completion(SimpleCompletionRequest)
     */
    @Override
    public ChatResponse completion(SimpleCompletionRequest request) {
        List<Message> messages = processCompletionMessages(request);
        ChatOptions options = processCompletionOptions(request);
        return this.completion(new Prompt(messages, options));
    }

    /**
     * @see ChatService#completion(Prompt)
     */
    @Override
    public ChatResponse completion(Prompt prompt) {
        return this.chatModel.call(prompt);
    }

    /**
     * @see ChatService#chatCompletion(SimpleChatRequest)
     */
    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(this.chatClient, request);
        return spec.call().chatResponse();
    }

    /**
     * @see ChatService#chatCompletion(SimpleChatRequest)
     */
    @Override
    public ChatResponse chatCompletion(Prompt prompt) {
        return this.chatClient.prompt(prompt).call().chatResponse();
    }

    /**
     * @see ChatService#chatCompletionText(SimpleChatRequest)
     */
    @Override
    public String chatCompletionText(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(this.chatClient, request);
        return spec.call().content();
    }

    /**
     * @see ChatService#streamChatCompletionText(SimpleChatRequest)
     */
    @Override
    public Flux<String> streamChatCompletionText(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(this.chatClient, request);
        return spec.stream().content();
    }

    /**
     * @see ChatService#streamChatCompletion(SimpleChatRequest)
     */
    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(this.chatClient, request);
        return spec.stream().chatResponse();
    }

    /**
     * @see ChatService#streamChatCompletion(Prompt)
     */
    @Override
    public Flux<ChatResponse> streamChatCompletion(Prompt prompt) {
        return this.chatClient.prompt(prompt).stream().chatResponse();
    }

}
