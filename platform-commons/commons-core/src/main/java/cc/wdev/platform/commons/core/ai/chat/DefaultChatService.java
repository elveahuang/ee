package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.platform.commons.core.ai.AiCustomizer;
import cc.wdev.platform.commons.core.ai.advisor.CustomLoggingAdvisor;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import cc.wdev.platform.commons.utils.StringUtils;
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
public class DefaultChatService implements ChatService {

    private final ChatClient client;

    public DefaultChatService(OpenAiChatModel model,
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
     * @see ChatService#getChatClient()
     */
    @Override
    public ChatClient getChatClient() {
        return this.client;
    }

    /**
     * @see ChatService#completion(SimpleCompletionRequest)
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
     * @see ChatService#completionText(SimpleCompletionRequest)
     */
    @Override
    public String completionText(SimpleCompletionRequest request) {
        OpenAiChatOptions options = new OpenAiChatOptions();
        return this.client
            .prompt(request.getPrompt())
            .options(options)
            .call()
            .content();
    }

    /**
     * @see ChatService#chatCompletion(SimpleChatRequest)
     */
    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()));

        // 添加系统提示词
        if (request.getSystemPrompt() != null && !request.getSystemPrompt().isEmpty()) {
            spec = spec.system(request.getSystemPrompt());
        }

        return spec.call().chatResponse();
    }

    /**
     * @see ChatService#chatCompletionText(SimpleChatRequest)
     */
    @Override
    public String chatCompletionText(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()));
        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        if (request.getTool() != null) {
            spec = spec.toolNames(request.getTool());
        }
        return spec.call().content();
    }

    /**
     * @see ChatService#streamChatCompletion(SimpleChatRequest)
     */
    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()));
        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        return spec.stream().chatResponse();
    }

    /**
     * @see ChatService#streamChatCompletionText(SimpleChatRequest)
     */
    @Override
    public Flux<String> streamChatCompletionText(SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()));
        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        return spec.stream().content();

    }

}
