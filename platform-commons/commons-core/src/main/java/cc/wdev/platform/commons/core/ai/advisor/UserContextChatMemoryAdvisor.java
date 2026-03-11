package cc.wdev.platform.commons.core.ai.advisor;

import cc.wdev.platform.commons.core.ai.AiConstants;
import cc.wdev.platform.commons.utils.StringUtils;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * 用户上下文聊天记忆 Advisor
 * <p>
 * 功能：确保 AI 回复（AssistantMessage）继承用户消息（UserMessage）的上下文信息（entityId 和 entityType）
 * <p>
 * 工作原理：
 * Order=150 > MessageChatMemoryAdvisor(Order=100)，确保在 MessageChatMemoryAdvisor 保存之前注入用户信息
 *
 * @author elvea
 */
@Slf4j
public class UserContextChatMemoryAdvisor implements CallAdvisor, StreamAdvisor {

    @NonNull
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        // Order > MessageChatMemoryAdvisor(100)，确保在"内层"
        // 这样会在 MessageChatMemoryAdvisor 保存之前执行
        return 150;
    }

    @NonNull
    @Override
    public ChatClientResponse adviseCall(@NonNull ChatClientRequest advisedRequest, CallAdvisorChain chain) {
        // 1. 提取用户信息（从 UserMessage 的 metadata）
        Long entityId = extractEntityId(advisedRequest);
        String entityType = extractEntityType(advisedRequest);

        // 2. 调用下一个 Advisor（AI 调用）
        ChatClientResponse advisedResponse = chain.nextCall(advisedRequest);

        // 3. 注入用户信息到 AssistantMessage
        if (entityId != null && StringUtils.isNotEmpty(entityType)) {
            injectUserContext(advisedResponse.chatResponse(), entityId, entityType);
        }

        return advisedResponse;
    }

    @NonNull
    @Override
    public Flux<ChatClientResponse> adviseStream(@NonNull ChatClientRequest advisedRequest, StreamAdvisorChain chain) {
        // 1. 提取用户信息
        Long entityId = extractEntityId(advisedRequest);
        String entityType = extractEntityType(advisedRequest);

        // 2. 流式调用，对每个响应注入用户信息
        return chain.nextStream(advisedRequest)
            .doOnNext(advisedResponse -> {
                if (entityId != null && StringUtils.isNotEmpty(entityType)
                    && advisedResponse != null && advisedResponse.chatResponse() != null) {
                    injectUserContext(advisedResponse.chatResponse(), entityId, entityType);
                }
            });
    }

    /**
     * 从请求中提取 entityId
     */
    private Long extractEntityId(ChatClientRequest request) {
        // 从 prompt 的 instructions 中提取
        for (Message message : request.prompt().getInstructions()) {
            if (message.getMessageType() == MessageType.USER) {
                // 检查是否是新消息（没有数据库 ID）
                Long messageId = MapUtil.getLong(message.getMetadata(), AiConstants.METADATA_CHAT_MEMORY_ID, null);
                if (messageId == null) {
                    Long entityId = MapUtil.getLong(message.getMetadata(), AiConstants.METADATA_ENTITY_ID, null);
                    if (entityId != null) {
                        return entityId;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从请求中提取 entityType
     */
    private String extractEntityType(ChatClientRequest request) {
        for (Message message : request.prompt().getInstructions()) {
            if (message.getMessageType() == MessageType.USER) {
                // 检查是否是新消息（没有数据库 ID）
                Long messageId = MapUtil.getLong(message.getMetadata(), AiConstants.METADATA_CHAT_MEMORY_ID, null);
                if (messageId == null) {
                    String entityType = MapUtil.getStr(message.getMetadata(), AiConstants.METADATA_ENTITY_TYPE, null);
                    if (StringUtils.isNotEmpty(entityType)) {
                        return entityType;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 注入用户上下文到 AssistantMessage
     */
    private void injectUserContext(ChatResponse chatResponse, Long entityId, String entityType) {
        if (chatResponse == null || chatResponse.getResults().isEmpty()) {
            return;
        }

        chatResponse.getResults().forEach(result -> {
            AssistantMessage assistantMessage = result.getOutput();
            if (assistantMessage != null) {
                // 检查是否已经有这些 metadata（避免重复注入）
                if (assistantMessage.getMetadata().containsKey(AiConstants.METADATA_ENTITY_ID)) {
                    return;
                }

                // 直接修改 metadata（AssistantMessage 的 metadata 是可变的）
                assistantMessage.getMetadata().put(AiConstants.METADATA_ENTITY_ID, entityId);
                assistantMessage.getMetadata().put(AiConstants.METADATA_ENTITY_TYPE, entityType);

                log.debug("Injected user context to AssistantMessage: entityId={}, entityType={}", entityId, entityType);
            }
        });
    }

}
