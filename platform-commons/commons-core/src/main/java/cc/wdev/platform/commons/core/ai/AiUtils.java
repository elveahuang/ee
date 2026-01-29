package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleCompletionRequest;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;

import java.util.List;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author elvea
 */
public abstract class AiUtils {

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
        ToolCallingChatOptions.Builder builder = ToolCallingChatOptions.builder();
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
        ChatClient.ChatClientRequestSpec spec = chatClient
            .prompt()
            .user(u -> {
                u.text(request.getPrompt());
                if (request.getEntityId() != null) {
                    u.metadata(AiConstants.METADATA_ENTITY_ID, request.getEntityId());
                }
                if (StringUtils.isNotEmpty(request.getEntityType())) {
                    u.metadata(AiConstants.METADATA_ENTITY_TYPE, request.getEntityType());
                }
            })
            .advisors(a -> a.param(CONVERSATION_ID, StringUtils.nvl(request.getConversationId(), StringUtils.uuid())));

        if (StringUtils.isNotEmpty(request.getSystemPrompt())) {
            spec = spec.system(request.getSystemPrompt());
        }
        return spec;
    }

}
