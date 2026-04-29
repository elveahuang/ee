package cc.wdev.platform.commons.ai.utils;

import cc.wdev.platform.commons.ai.AiConstants;
import cc.wdev.platform.commons.ai.domain.AiContentVo;
import cc.wdev.platform.commons.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.ai.enums.AiContentType;
import cc.wdev.platform.commons.utils.*;
import org.apache.commons.collections4.MapUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Optional;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public abstract class AiUtils {

    public static AiContentVo STREAM_CONTENT_START = AiContentVo.builder().type(AiContentType.START.getValue()).build();

    public static AiContentVo STREAM_CONTENT_END = AiContentVo.builder().type(AiContentType.END.getValue()).build();

    public static AiContentVo STREAM_CONTENT_ERROR = AiContentVo.builder().type(AiContentType.ERROR.getValue()).build();

    /**
     * 生成新的对话ID
     */
    public static String generateConversationId() {
        return StringUtils.uuid();
    }

    /**
     * 处理提示词
     */
    public static String renderPrompt(String prompt, Map<String, Object> context) {
        if (MapUtils.isNotEmpty(context)) {
            PromptTemplate promptTemplate = PromptTemplate
                .builder()
                .template(prompt)
                .variables(context)
                .build();
            return promptTemplate.render();
        }
        return prompt;
    }

    // ------------------------------------------------------------------------------
    // Stream Content
    // ------------------------------------------------------------------------------

    public static String getStartContent() {
        return GsonUtils.toJson(STREAM_CONTENT_START);
    }

    public static String getEndContent() {
        return GsonUtils.toJson(STREAM_CONTENT_END);
    }

    public static String getErrorContent() {
        return GsonUtils.toJson(STREAM_CONTENT_ERROR);
    }

    public static String getTextContent(String text) {
        return GsonUtils.toJson(AiContentVo.builder().type(AiContentType.TEXT.getValue()).content(text).build());
    }

    // ------------------------------------------------------------------------------
    // ChatClient
    // ------------------------------------------------------------------------------

    public static ChatResponse chatCompletion(ChatClient chatClient, SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(chatClient, request);
        return spec.call().chatResponse();
    }

    public static ChatResponse chatCompletion(ChatClient chatClient, Prompt prompt) {
        return chatClient.prompt(prompt).call().chatResponse();
    }

    public static String chatCompletionText(ChatClient chatClient, SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(chatClient, request);
        return spec.call().content();
    }

    public static Flux<String> streamChatCompletionText(ChatClient chatClient, SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(chatClient, request);
        return spec.stream().content();
    }

    public static Flux<ChatResponse> streamChatCompletion(ChatClient chatClient, SimpleChatRequest request) {
        ChatClient.ChatClientRequestSpec spec = processChatSpec(chatClient, request);
        return spec.stream().chatResponse();
    }

    public static Flux<ChatResponse> streamChatCompletion(ChatClient chatClient, Prompt prompt) {
        return chatClient.prompt(prompt).stream().chatResponse();
    }

    // ------------------------------------------------------------------------------
    // Utils
    // ------------------------------------------------------------------------------

    public static ChatClient.ChatClientRequestSpec processChatSpec(ChatClient chatClient, SimpleChatRequest request) {
        // userId
        if (!ObjectUtils.isValidId(request.getUserId())) {
            request.setUserId(SecurityUtils.getUid());
        }
        ChatClient.ChatClientRequestSpec spec = chatClient
            .prompt()
            .user(u -> {
                u.text(request.getPrompt());
                if (request.getUserId() != null) {
                    u.metadata(AiConstants.METADATA_USER_ID, request.getUserId());
                }
                if (StringUtils.isNotEmpty(request.getChatType())) {
                    u.metadata(AiConstants.CHAT_TYPE, request.getChatType());
                }
            })
            .toolContext(Map.of(AiConstants.METADATA_USER_ID, request.getUserId()))
            .advisors(a -> a.param(CONVERSATION_ID, StringUtils.nvl(request.getConversationId(), StringUtils.uuid())));

        if (CollectionUtils.isNotEmpty(request.getToolNames())) {
            spec = spec.toolNames(request.getToolNames().toArray(new String[0]));
        }
        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        return spec;
    }

    public static @Nullable String getChatResponseContent(ChatResponse chatResponse) {
        return Optional.ofNullable(chatResponse)
            .map(ChatResponse::getResult)
            .map(Generation::getOutput)
            .map(AbstractMessage::getText)
            .orElse(null);
    }

}
