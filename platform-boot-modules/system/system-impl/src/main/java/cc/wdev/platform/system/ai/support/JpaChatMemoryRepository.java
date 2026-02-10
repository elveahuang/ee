package cc.wdev.platform.system.ai.support;

import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.ai.domain.entity.AiChatMemoryEntity;
import cc.wdev.platform.system.ai.service.AiChatMemoryService;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class JpaChatMemoryRepository implements ChatMemoryRepository {

    private final AiChatMemoryService aiChatMemoryService;

    /**
     * @see ChatMemoryRepository#findConversationIds()
     */
    @Override
    public @NonNull List<String> findConversationIds() {
        return this.aiChatMemoryService.findConversationIds();
    }

    /**
     * @see ChatMemoryRepository#findByConversationId(String)
     */
    @Override
    public @NonNull List<Message> findByConversationId(@NonNull String conversationId) {
        List<AiChatMemoryEntity> entityList = aiChatMemoryService.findByConversationId(conversationId);
        if (CollectionUtils.isEmpty(entityList)) {
            return List.of();
        }

        if (CollectionUtils.isNotEmpty(entityList)) {
            return entityList.stream().map((entity) -> {
                Map<String, Object> metadata = Maps.newHashMap();
                metadata.put("AiChatMemoryId", entity.getId());

                String content = entity.getContent();
                String type = entity.getType();
                return switch (MessageType.valueOf(type)) {
                    case MessageType.USER -> UserMessage.builder().text(content).metadata(metadata).build();
                    case MessageType.ASSISTANT -> AssistantMessage.builder().content(content).properties(metadata).build();
                    case MessageType.SYSTEM -> SystemMessage.builder().text(content).metadata(metadata).build();
                    case MessageType.TOOL -> ToolResponseMessage.builder().metadata(metadata).build();
                };
            }).collect(Collectors.toUnmodifiableList());
        }
        return List.of();
    }

    @Override
    public void saveAll(@NonNull String conversationId, @NonNull List<Message> messages) {
        this.aiChatMemoryService.deleteByConversationId(conversationId);

        if (CollectionUtils.isNotEmpty(messages)) {
            List<AiChatMemoryEntity> entityList = messages.stream().map((message) -> AiChatMemoryEntity.builder()
                .conversationId(conversationId)
                .content(message.getText())
                .type(message.getMessageType().getValue()).build()
            ).toList();
            this.aiChatMemoryService.saveBatch(entityList);
        }
    }

    @Override
    public void deleteByConversationId(@NonNull String conversationId) {
        this.aiChatMemoryService.deleteByConversationId(conversationId);
    }

}
