package cc.wdev.platform.commons.core.ai.advisor;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.client.ChatClientMessageAggregator;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 */
@Slf4j
public class CustomLoggingAdvisor implements CallAdvisor, StreamAdvisor {

    @NonNull
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @NonNull
    @Override
    public ChatClientResponse adviseCall(@NonNull ChatClientRequest advisedRequest, CallAdvisorChain chain) {
        log.info("AI adviseCall.");
        ChatClientResponse advisedResponse = chain.nextCall(this.logRequest(advisedRequest));
        this.logResponses(advisedResponse);
        return advisedResponse;
    }

    @NonNull
    @Override
    public Flux<ChatClientResponse> adviseStream(@NonNull ChatClientRequest advisedRequest, StreamAdvisorChain chain) {
        log.info("AI adviseStream.");
        Flux<ChatClientResponse> advisedResponses = chain.nextStream(this.logRequest(advisedRequest));
        return (new ChatClientMessageAggregator()).aggregateChatClientResponse(advisedResponses, this::logResponses);
    }

    private ChatClientRequest logRequest(ChatClientRequest request) {
        log.info("AI Request: {}", request.prompt().getContents());
        log.info("AI Request Message: {}", request.context());
        return request;
    }

    private void logResponses(ChatClientResponse advisedResponse) {
        ChatResponse response = advisedResponse.chatResponse();
        if (null == response) {
            log.info("AI Response is null");
            return;
        }

        ChatResponseMetadata responseMetadata = response.getMetadata();
        Usage usage = responseMetadata.getUsage();
        log.info("Tokens ：{}", usage.getTotalTokens());
        log.info("Input tokens：{}", usage.getPromptTokens());
        log.info("Output tokens：{}", usage.getCompletionTokens());

        AssistantMessage assistantMessage = response.getResult().getOutput();
        List<AssistantMessage.ToolCall> toolCallList = assistantMessage.getToolCalls();
        String result = assistantMessage.getText();
        log.info("Prompt：{}", result);
        log.info("Tools: {}", toolCallList.size());
        String toolCallInfo = toolCallList.stream().map(toolCall -> String.format("Tool：%s，Params：%s", toolCall.name(), toolCall.arguments())).collect(Collectors.joining("\n"));
        log.info(toolCallInfo);
        log.info("AI Response: {}", assistantMessage.getText());
    }

}
