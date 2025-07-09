package cc.elvea.platform.commons.core.ai.chat;

import cc.elvea.platform.commons.core.ai.AiCustomizer;
import cc.elvea.platform.commons.core.ai.advisor.CustomLoggingAdvisor;
import cc.elvea.platform.commons.core.ai.model.request.SimpleChatRequest;
import cc.elvea.platform.commons.core.ai.model.request.SimpleChatRequestCustomizer;
import cc.elvea.platform.commons.core.ai.model.request.SimpleCompletionRequest;
import cc.elvea.platform.commons.core.ai.model.request.SimpleCompletionRequestCustomizer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.ObjectProvider;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public class DefaultChatCompletionService implements ChatCompletionService {

    private final ChatClient client;

    public DefaultChatCompletionService(DeepSeekChatModel model,
                                        MessageWindowChatMemory messageWindowChatMemory,
                                        ObjectProvider<AiCustomizer> customizerProvider) {
        AiCustomizer customizer = customizerProvider.getIfAvailable(AiCustomizer::defaultCustomizer);
        this.client = ChatClient.builder(model)
            .defaultAdvisors(
                MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build(),
                new CustomLoggingAdvisor()
            ).defaultTools(customizer.getTools().toArray())
            .build();
    }

    @Override
    public ChatClient getChatClient() {
        return this.client;
    }

    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request) throws Exception {
        return this.chatCompletion(request, SimpleChatRequestCustomizer.builder().build());
    }

    @Override
    public ChatResponse chatCompletion(SimpleChatRequest request,
                                       SimpleChatRequestCustomizer customizer) throws Exception {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .call()
            .chatResponse();
    }

    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request) throws Exception {
        return this.streamChatCompletion(request, SimpleCompletionRequestCustomizer.builder().build());
    }

    @Override
    public Flux<ChatResponse> streamChatCompletion(SimpleChatRequest request,
                                                   SimpleCompletionRequestCustomizer customizer) throws Exception {
        return this.client
            .prompt(request.getPrompt())
            .advisors(a -> a.param(CONVERSATION_ID, request.getConversationId()))
            .tools(customizer.getTools())
            .stream()
            .chatResponse();
    }

    @Override
    public ChatResponse completion(SimpleCompletionRequest request) throws Exception {
        OpenAiChatOptions options = new OpenAiChatOptions();
        return this.client
            .prompt(request.getPrompt())
            .options(options)
            .call()
            .chatResponse();

    }

    @Override
    public String completionText(SimpleCompletionRequest request) throws Exception {
        ChatResponse response = this.completion(request);
        if (response == null ||
            response.getResult() == null ||
            response.getResult().getOutput() == null ||
            response.getResult().getOutput().getText() == null) {
            return "";
        }
        return response.getResult().getOutput().getText();
    }

}
