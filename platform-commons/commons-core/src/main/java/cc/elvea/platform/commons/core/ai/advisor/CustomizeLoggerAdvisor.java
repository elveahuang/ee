package cc.elvea.platform.commons.core.ai.advisor;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 */
@Slf4j
public class CustomizeLoggerAdvisor implements CallAdvisor, StreamAdvisor {

    @NotNull
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private ChatClientRequest before(ChatClientRequest request) {
        log.info("AI Request: {}", request.prompt().getContents());
        log.info("AI Request Message: {}", request.context());
        return request;
    }

    private void observeAfter(ChatClientResponse advisedResponse) {
        ChatResponse response = advisedResponse.chatResponse();
        if (null == response) {
            log.info("AI Response is null");
            return;
        }
        ChatResponseMetadata responseMetadata = response.getMetadata();
        // 输出使用的tokens数
        Usage usage = responseMetadata.getUsage();
        log.info("已使用的总tokens数：{}", usage.getTotalTokens());
        log.info("输入的tokens数：{}", usage.getPromptTokens());
        log.info("输出的tokens数：{}", usage.getCompletionTokens());
        AssistantMessage assistantMessage = response.getResult().getOutput();
        List<AssistantMessage.ToolCall> toolCallList = assistantMessage.getToolCalls();
        // 输出提示信息
        String result = assistantMessage.getText();
        log.info("问题：{}", result);
        log.info("选择了 [{}] 个工具来使用", toolCallList.size());
        String toolCallInfo = toolCallList.stream()
            .map(toolCall -> String.format("工具名称：%s，参数：%s", toolCall.name(), toolCall.arguments()))
            .collect(Collectors.joining("\n"));
        log.info(toolCallInfo);
        log.info("AI Response: {}", assistantMessage.getText());
    }

    @NonNull
    @Override
    public ChatClientResponse adviseCall(@NonNull ChatClientRequest advisedRequest, CallAdvisorChain chain) {
        ChatClientResponse advisedResponse = chain.nextCall(this.before(advisedRequest));
        this.observeAfter(advisedResponse);
        return advisedResponse;
    }

    @NonNull
    @Override
    public Flux<ChatClientResponse> adviseStream(@NonNull ChatClientRequest advisedRequest, StreamAdvisorChain chain) {
        Flux<ChatClientResponse> advisedResponses = chain.nextStream(this.before(advisedRequest));
        return (new ChatClientMessageAggregator()).aggregateChatClientResponse(advisedResponses, this::observeAfter);
    }

}
