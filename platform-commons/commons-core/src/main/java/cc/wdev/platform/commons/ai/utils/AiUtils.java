package cc.wdev.platform.commons.ai.utils;

import cc.wdev.platform.commons.ai.AiConstants;
import cc.wdev.platform.commons.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.ai.domain.request.SimpleCompletionRequest;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SecurityUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public abstract class AiUtils {

    // ------------------------------------------------------------------------------
    // ChatModel
    // ------------------------------------------------------------------------------

    public static ChatResponse completion(ChatModel chatModel, SimpleCompletionRequest request) {
        List<Message> messages = processCompletionMessages(request);
        ChatOptions options = processCompletionOptions(request);
        return completion(chatModel, new Prompt(messages, options));
    }

    public static ChatResponse completion(ChatModel chatModel, Prompt prompt) {
        return chatModel.call(prompt);
    }

    public static String completionText(ChatModel chatModel, SimpleCompletionRequest request) {
        List<Message> messages = processCompletionMessages(request);
        ChatOptions options = processCompletionOptions(request);
        return completionText(chatModel, new Prompt(messages, options));
    }

    public static String completionText(ChatModel chatModel, Prompt prompt) {
        return chatModel.call(prompt).getResult().getOutput().getText();
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

    public static List<Message> processCompletionMessages(SimpleCompletionRequest request) {
        // 处理信息
        List<Message> messages = Lists.newArrayList();
        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            messages.add(new SystemMessage(request.getSystemPrompt()));
        }
        if (CollectionUtils.isNotEmpty(request.getMessages())) {
            messages.addAll(request.getMessages());
        }
        if (StringUtils.isNotEmpty(request.getPrompt())) {
            messages.add(new UserMessage(request.getPrompt()));
        }
        return messages;
    }

    public static ChatOptions processCompletionOptions(SimpleCompletionRequest request) {
        // 处理调用工具
        ToolCallingChatOptions.Builder<?> builder = ToolCallingChatOptions.builder();
        if (CollectionUtils.isNotEmpty(request.getTools())) {
            ToolCallback[] toolCallbacks = ToolCallbacks.from(request.getTools());
            builder.toolCallbacks(toolCallbacks);
        }

        // 处理工具上下文
        if (CollectionUtils.isNotEmpty(request.getContextMap())) {
            builder.toolContext(request.getContextMap());
        } else {
            builder.toolContext(Maps.newHashMap());
        }

        return builder.build();
    }

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
            .advisors(a -> a.param(CONVERSATION_ID, StringUtils.nvl(request.getConversationId(), StringUtils.uuid())));

        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        return spec;
    }

}
